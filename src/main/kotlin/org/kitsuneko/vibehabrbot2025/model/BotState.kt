package org.kitsuneko.vibehabrbot2025.model

class BotState {
    private var started: Boolean = false
    private var paused: Boolean = false

    fun start() {
        started = true
    }

    fun isStarted(): Boolean {
        return started
    }

    fun pause() {
        paused = true
    }

    fun resume() {
        paused = false
    }

    fun isPaused(): Boolean {
        return paused
    }

}