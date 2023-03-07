package io.bry1337.pomfocus.android.model

import androidx.annotation.StringRes
import io.bry1337.pomfocus.android.R
import io.bry1337.pomfocus.android.utils.TimerConstants

/**
 * Created by Bryan on 3/7/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

data class Pomodoro(
    @StringRes val mainActionButtonLabel: Int,
    @StringRes val pomodoroTitle: Int,
    @StringRes val pomodoroQuote: Int,
    val pomodoroTotalSeconds: Int,
    val pomodoroTotalMinutes: Int,
    val isForwardable: Boolean,
    val state: PomodoroState
) {
    companion object {
        val mockData: Pomodoro
            get() = Pomodoro(
                mainActionButtonLabel = R.string.home_screen_start_button,
                pomodoroTitle = R.string.home_screen_focus_title_1,
                pomodoroQuote = R.string.home_screen_focus_label_1,
                pomodoroTotalSeconds = TimerConstants.DEFAULT_POMODORO_TOTAL_SECONDS,
                pomodoroTotalMinutes = TimerConstants.DEFAULT_POMODORO_MINUTES,
                isForwardable = false,
                state = PomodoroState.POMODORO_START
            )

        val pomodoroList: Array<Pomodoro>
            get() = arrayOf(
                Pomodoro(
                    mainActionButtonLabel = R.string.home_screen_start_button,
                    pomodoroTitle = R.string.home_screen_focus_title_1,
                    pomodoroQuote = R.string.home_screen_focus_label_1,
                    pomodoroTotalSeconds = TimerConstants.DEFAULT_POMODORO_TOTAL_SECONDS,
                    pomodoroTotalMinutes = TimerConstants.DEFAULT_POMODORO_MINUTES,
                    isForwardable = false,
                    state = PomodoroState.POMODORO_START
                ),
                Pomodoro(
                    mainActionButtonLabel = R.string.home_screen_pause_button,
                    pomodoroTitle = R.string.home_screen_focus_title_1,
                    pomodoroQuote = R.string.home_screen_focus_label_2,
                    pomodoroTotalSeconds = TimerConstants.DEFAULT_POMODORO_TOTAL_SECONDS,
                    pomodoroTotalMinutes = TimerConstants.DEFAULT_POMODORO_MINUTES,
                    isForwardable = true,
                    state = PomodoroState.POMODORO_RUNNING
                ),
                Pomodoro(
                    mainActionButtonLabel = R.string.home_screen_start_button,
                    pomodoroTitle = R.string.home_screen_focus_title_2,
                    pomodoroQuote = R.string.home_screen_focus_label_3,
                    pomodoroTotalSeconds = TimerConstants.DEFAULT_BREAK_TOTAL_SECONDS,
                    pomodoroTotalMinutes = TimerConstants.DEFAULT_BREAK_MINUTES,
                    isForwardable = false,
                    state = PomodoroState.BREAK_START
                ),
                Pomodoro(
                    mainActionButtonLabel = R.string.home_screen_pause_button,
                    pomodoroTitle = R.string.home_screen_focus_title_2,
                    pomodoroQuote = R.string.home_screen_focus_label_4,
                    pomodoroTotalSeconds = TimerConstants.DEFAULT_BREAK_TOTAL_SECONDS,
                    pomodoroTotalMinutes = TimerConstants.DEFAULT_BREAK_MINUTES,
                    isForwardable = false,
                    state = PomodoroState.BREAK_RUNNING
                )
            )
    }
}

enum class PomodoroState(val state: String, val index: Int) {
    POMODORO_START("pomodoro_start", 0),
    POMODORO_RUNNING("pomodoro_running", 1),
    BREAK_START("break_start", 2),
    BREAK_RUNNING("break_running", 3)
}