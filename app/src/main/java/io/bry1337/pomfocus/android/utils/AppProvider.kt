package io.bry1337.pomfocus.android.utils

/**
 * Created by Bryan on 10/24/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

object AppProvider {
    private const val application = "application"
    private val providers = mutableMapOf<String, () -> Any>()

    fun registerApplicationProvider(provider: () -> Any): AppProvider {
        providers[application] = provider
        return this
    }

    fun getApplication(): Any? = providers[application]?.invoke()
}
