package io.bry1337.pomfocus

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
