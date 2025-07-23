package org.kitsuneko.vibehabrbot2025.service.command.handlers

import org.kitsuneko.vibehabrbot2025.service.command.Command
import org.kitsuneko.vibehabrbot2025.service.state.ContestParticipantsStateService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.message.Message

@Service
class CommandPermissionHandlerService(
    private val participantsStateService: ContestParticipantsStateService
) {

    fun isAllowed(msg: Message, command: Command): Boolean {
        val username = "@" + msg.from.userName

        return !command.needAdmin() || participantsStateService.isAdmin(username)
    }

}