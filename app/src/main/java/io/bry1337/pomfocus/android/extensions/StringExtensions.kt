package io.bry1337.pomfocus.android.extensions

/**
 * Created by Bryan on 4/3/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

fun String.toCamelCase(): String {
    return this.lowercase().replaceFirstChar {
        it.uppercase()
    }.replace('_', ' ', true)
}
