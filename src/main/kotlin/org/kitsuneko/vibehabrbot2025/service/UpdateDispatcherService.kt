package org.kitsuneko.vibehabrbot2025.service

import org.kitsuneko.vibehabrbot2025.service.command.handlers.PrivateCommandHandlerService
import org.kitsuneko.vibehabrbot2025.service.command.handlers.StartCommandHandlerService
import org.kitsuneko.vibehabrbot2025.service.message.GroupMessageHandlerService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class UpdateDispatcherService(
    private val groupHandler: GroupMessageHandlerService,
    private val startHandler: StartCommandHandlerService,
    private val privateHandler: PrivateCommandHandlerService
) {

    fun dispatch(update: Update) {
        if (update.message == null) return
        val msg = update.message
        startHandler.handle(msg)
        when {
            msg.chat.isGroupChat || msg.chat.isSuperGroupChat -> groupHandler.handle(msg)
            msg.chat.isUserChat -> privateHandler.handle(msg)
        }
    }
}