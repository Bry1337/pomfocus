package io.bry1337.pomfocus.android.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.utils.TimerConstants
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Bryan on 3/6/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    private var timerScope: Job? = null
    private var isTimeRunning by mutableStateOf(false)
    private var pomodoroTotalTime: Int by mutableStateOf(TimerConstants.DEFAULT_TOTAL_SECONDS) // 1500 for 25 minutes
    private var runningMinutes by mutableStateOf(TimerConstants.DEFAULT_MINUTES) // 25 minutes default value
    private var runningSeconds by mutableStateOf(TimerConstants.DEFAULT_SECONDS)
    private val pomodoroTimeFlow = (pomodoroTotalTime downTo 0).asFlow()
    var formattedRunningTime by mutableStateOf(
        String.format(
            "%02d:%02d",
            runningMinutes,
            runningSeconds
        )
    )
        private set
    var timeFunctionLabel by mutableStateOf(R.string.home_screen_start_button)
        private set

    fun startRunningTime() {
        setTimeAndLabel()
        if (isTimeRunning) {
            timerScope = viewModelScope.launch {
                pomodoroTimeFlow.cancellable().collect {
                    delay(1000L)
                    setRunningTime()
                }
            }
        } else {
            timerScope?.cancel()
        }
    }

    private fun setRunningTime() {
        if (runningMinutes == 0 && runningSeconds == 0) {
            resetValues()
            return
        } else if (runningSeconds == 0) {
            runningMinutes -= 1
            runningSeconds = 60
        }
        runningSeconds -= 1
        setFormattedRunningTime()
    }

    private fun resetValues() {
        timerScope?.cancel()
        setTimeAndLabel()
        runningMinutes = TimerConstants.DEFAULT_MINUTES
        pomodoroTotalTime = TimerConstants.DEFAULT_TOTAL_SECONDS
        setFormattedRunningTime()
    }

    private fun setTimeAndLabel() {
        timeFunctionLabel = if (isTimeRunning) {
            R.string.home_screen_start_button
        } else {
            R.string.home_screen_pause_button
        }
        isTimeRunning = !isTimeRunning
    }

    private fun setFormattedRunningTime() {
        formattedRunningTime = String.format(
            "%02d:%02d",
            runningMinutes,
            runningSeconds
        )
    }
}
