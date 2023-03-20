package io.bry1337.pomfocus.domain.utils.generator

import kotlin.random.Random

/**
 * Created by Bryan on 3/16/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

interface Generator<T> {
    fun build(overrides: Map<String, Any> = emptyMap()): T

    suspend fun save(entity: T? = null): T

    fun buildMany(
        count: Int = Random.nextInt() + 1,
        overrides: Map<String, Any> = emptyMap()
    ): List<T> {
        return (1..count).map {
            build(overrides = overrides)
        }
    }

    fun <T> getOverrideOr(overrides: Map<String, Any>, key: String, or: T): T {
        @Suppress("UNCHECKED_CAST")
        return (overrides[key] as? T) ?: or
    }
}
