package org.kitsuneko.vibehabrbot2025.service

import org.kitsuneko.vibehabrbot2025.service.state.ContestParticipantsStateService
import org.kitsuneko.vibehabrbot2025.service.state.BotStateService
import org.springframework.stereotype.Service

@Service
class InitializerService(
    private val participantsStateService: ContestParticipantsStateService,
    private val botStateService: BotStateService
) {
    fun initialize(initiatorUsername: String): Boolean {
        if (botStateService.isStarted()) return false
        if (participantsStateService.getAdmins().isEmpty()) {
            participantsStateService.setAdmins(listOf(initiatorUsername))
        }
        botStateService.start()
        return true
    }
}