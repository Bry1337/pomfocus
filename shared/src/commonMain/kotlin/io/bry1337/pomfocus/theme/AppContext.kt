package io.bry1337.pomfocus.theme

/**
 * Created by Bryan on 3/8/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

object AppContext {
    private const val keyContext = "context"
    private val providers = mutableMapOf<String, () -> Any>()

    fun registerContextProvider(provider: () -> Any): AppContext {
        providers[keyContext] = provider
        return this
    }

    fun getContext(): Any? =
        providers[keyContext]?.invoke()
}
