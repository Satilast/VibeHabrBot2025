package org.kitsuneko.vibehabrbot2025.service.state

import org.kitsuneko.vibehabrbot2025.model.BotState
import org.springframework.stereotype.Service

@Service
class BotStateService {

    private val state = BotState()

    fun start() = state.start()

    fun pause() = state.pause()

    fun resume() = state.resume()

    fun isStarted() = state.isStarted()

    fun isPaused() = state.isPaused()

}