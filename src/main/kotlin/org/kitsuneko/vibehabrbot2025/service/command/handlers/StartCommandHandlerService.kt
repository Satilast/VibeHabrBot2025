package org.kitsuneko.vibehabrbot2025.service.command.handlers

import org.kitsuneko.vibehabrbot2025.service.command.impl.StartCommand
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.message.Message

@Service
class StartCommandHandlerService(
    private val command: StartCommand,
) {

    fun handle(msg: Message) {
        if (msg.text == null || !msg.text.equals("/start")) return
        command.execute(msg)
    }
}