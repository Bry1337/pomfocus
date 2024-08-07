package io.bry1337.pomfocus.android.ui.home

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.model.Pomodoro
import io.bry1337.pomfocus.android.model.PomodoroState
import io.bry1337.pomfocus.android.ui.notification.NotificationProvider
import io.bry1337.pomfocus.android.utils.TimerConstants
import io.bry1337.pomfocus.db.DatabaseManager
import io.bry1337.pomfocus.db.DatabaseModelOps
import io.bry1337.pomfocus.db.TaskOpsImpl
import io.bry1337.pomfocus.domain.models.Task
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Bryan on 3/6/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {
    private val dbOps by lazy {
        @Suppress("UNCHECKED_CAST")
        DatabaseManager(managerOps = TaskOpsImpl() as DatabaseModelOps<Any>)
    }
    val pomodoroModels = Pomodoro.pomodoroList
    var pomodoroCurrentIndex by mutableStateOf(0)
    var currentPomodoroState by mutableStateOf(PomodoroState.POMODORO_START)
        private set
    private var timerScope: Job? = null
    private var pomodoroTotalTime: Int by mutableStateOf(pomodoroModels[pomodoroCurrentIndex].pomodoroTotalSeconds) // 1500 for 25 minutes
    private var runningMinutes by mutableStateOf(pomodoroModels[pomodoroCurrentIndex].pomodoroTotalMinutes) // 25 minutes default value
    private var runningSeconds by mutableStateOf(TimerConstants.DEFAULT_SECONDS)
    private var isTimeRunning by mutableStateOf(false)
    private var pomodoroTimeFlow = (pomodoroTotalTime downTo 0).asFlow()
    var showSaveOption by mutableStateOf(false)
    var taskList: List<Task> by mutableStateOf(listOf<Task>())
    var showToastMessage by mutableStateOf(false)
        private set
    var toastMessage by mutableStateOf(0)
        private set
    var formattedRunningTime by mutableStateOf(
        String.format(
            "%02d:%02d",
            runningMinutes,
            runningSeconds
        )
    )
        private set

    init {
        buildEmptyTask()
    }

    /**
     * Populate empty task for listing task
     */
    private fun buildEmptyTask() {
        taskList = taskList.toMutableList().also {
            it.clear()
            it.add(Task.buildEmpty())
        }
    }

    /**
     * Forward function
     */
    fun forwardPomodoroState() {
        // TO FORWARD POMODORO PHASE
        updateValues()
    }

    /**
     * Index should be decremented when placed on PAUSE state and
     * incremented when placed on RUNNING state
     */
    private fun toggleTimerState() {
        if (pomodoroCurrentIndex % 2 != 0 && isTimeRunning) {
            pomodoroCurrentIndex -= 1 // TO PAUSE STATE
        } else {
            pomodoroCurrentIndex += 1 // TO RUN TIMER
        }
    }

    /**
     * Run the flow asynchronously and pause it depending if the time is running
     */
    fun togglePomodoroMainAction() {
        toggleTimerState()
        isTimeRunning = !isTimeRunning
        if (isTimeRunning) {
            timerScope = viewModelScope.launch {
                pomodoroTimeFlow.cancellable().collect {
                    delay(1000L)
                    updateRunningTime()
                }
            }
        } else {
            timerScope?.cancel()
        }
    }

    /**
     * Updates the values that is displayed in the view
     */
    private fun updateRunningTime() {
        if (runningMinutes == 0 && runningSeconds == 0) {
            updateValues()
            return
        } else if (runningSeconds == 0) {
            runningMinutes -= 1
            runningSeconds = TimerConstants.DEFAULT_FULL_SECONDS
        }
        runningSeconds -= 1
        formatRunningTime()
    }

    /**
     * Updates the rest of the states depending if the state is forwarded or
     * the time has run out
     */
    private fun updateValues() {
        timerScope?.cancel()
        stateProcessor()
        formatRunningTime()
    }

    /**
     * Format the time to be sent back to view
     */
    private fun formatRunningTime() {
        formattedRunningTime = String.format(
            "%02d:%02d",
            runningMinutes,
            runningSeconds
        )
    }

    /**
     * Checks the state and updates the values depending on the state.
     * This also assign new values to flow to update the time constraint.
     */
    private fun stateProcessor() {
        when (pomodoroModels[pomodoroCurrentIndex].state) {
            PomodoroState.POMODORO_START, PomodoroState.POMODORO_RUNNING -> {
                pomodoroCurrentIndex = PomodoroState.BREAK_START.index
                runningSeconds = TimerConstants.DEFAULT_SECONDS
                runningMinutes = pomodoroModels[pomodoroCurrentIndex].pomodoroTotalMinutes
                pomodoroTotalTime = pomodoroModels[pomodoroCurrentIndex].pomodoroTotalSeconds
                pomodoroTimeFlow = (pomodoroTotalTime downTo 0).asFlow()
                showNotification(
                    notifTitle = R.string.notification_pomodoro_finished_title,
                    notifContent = R.string.notification_pomodoro_finished
                )
                val message = if (taskList.size > 1) {
                    R.string.toast_message_fail_1
                } else {
                    R.string.toast_message_success_1
                }
                showToastMessage(message)
                taskList = taskList.toMutableList().also {
                    it.clear()
                }
                // TODO Uncomment when saving of tasks is available
//                showSaveOption = true
            }

            PomodoroState.BREAK_START, PomodoroState.BREAK_RUNNING -> {
                pomodoroCurrentIndex = PomodoroState.POMODORO_START.index
                runningSeconds = TimerConstants.DEFAULT_SECONDS
                runningMinutes = pomodoroModels[pomodoroCurrentIndex].pomodoroTotalMinutes
                pomodoroTotalTime = pomodoroModels[pomodoroCurrentIndex].pomodoroTotalSeconds
                pomodoroTimeFlow = (pomodoroTotalTime downTo 0).asFlow()
                showNotification(
                    notifTitle =
                    R.string.notification_break_finished_title,
                    notifContent = R.string.notification_break_finished
                )
                buildEmptyTask()
            }
        }
        currentPomodoroState = pomodoroModels[pomodoroCurrentIndex].state
        isTimeRunning = !isTimeRunning
    }

    /**
     * Save tasks list in local device database
     */
    fun saveTasksListed() {
        viewModelScope.launch {
            dbOps.saveObjects(taskList)
            taskList = taskList.toMutableList().also {
                it.clear()
            }
        }
    }

    fun updateSaveDialog() {
        showSaveOption = !showSaveOption
        // Clear task list upon transitioning to break time
        taskList = taskList.toMutableList().also {
            it.clear()
        }
    }

    /**
     * Play notification sound queue in flow
     */
    private fun showNotification(@StringRes notifTitle: Int, @StringRes notifContent: Int) {
        viewModelScope.launch {
            NotificationProvider.showNotification(notifTitle, notifContent)
        }
    }

    fun addNewTasks() {
        taskList = taskList.toMutableList().also {
            it.add(Task.buildEmpty())
        }
    }

    /**
     * Delete tasks added in during pomodoro
     */
    fun deleteTask(index: Int, task: Task) {
        taskList = taskList.toMutableList().also {
            it.removeAt(index)
        }
    }

    fun showToastMessage(message: Int) {
        toastMessage = message
        viewModelScope.launch {
            flowOf(showToastMessage).map {
                showToastMessage = !it
            }.collect {
                delay(2000L)
                showToastMessage = !showToastMessage
            }
        }
    }
}
