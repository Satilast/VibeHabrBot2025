package org.kitsuneko.vibehabrbot2025.service.command.impl.privates

import org.kitsuneko.vibehabrbot2025.service.command.PrivateCommand
import org.kitsuneko.vibehabrbot2025.service.sender.SenderService
import org.kitsuneko.vibehabrbot2025.service.state.BotStateService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class ResumeCommand(
    private val botStateService: BotStateService,
    private val senderService: SenderService
) : PrivateCommand {

    override fun execute(message: Message) {
        val chatId = message.chatId
        botStateService.resume()
        senderService.sendMessage(chatId, "Возобновлена обработка сообщений в групповом чате")
    }

    override fun getName(): String = "/resume"

    override fun needAdmin(): Boolean = true

    override fun getDescription(): String = "Возобновляет обработку сообщений в групповом чате"

    override fun getFormat(): String = "/resume"

    override fun getUsage(): String = "/resume"

}