package io.bry1337.pomfocus.theme.palettes

/**
 * Created by Bryan on 2/25/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

internal class ColorSwatch(name: String, vararg toneColors: Pair<Int, Long>) {
    val name: String
    private val colorTones: MutableMap<Int, Long>

    init {
        this.name = name
        colorTones = mutableMapOf(*toneColors)
    }

    infix fun tone(x: Int): Long? = colorTones[x]
    fun asNamedPair() = name to this
}
