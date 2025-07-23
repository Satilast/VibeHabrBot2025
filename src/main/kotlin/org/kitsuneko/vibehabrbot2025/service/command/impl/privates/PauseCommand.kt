package org.kitsuneko.vibehabrbot2025.service.command.impl.privates

import org.kitsuneko.vibehabrbot2025.service.command.PrivateCommand
import org.kitsuneko.vibehabrbot2025.service.sender.SenderService
import org.kitsuneko.vibehabrbot2025.service.state.BotStateService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class PauseCommand(
    private val botStateService: BotStateService,
    private val senderService: SenderService
) : PrivateCommand {

    override fun execute(message: Message) {
        val chatId = message.chatId
        botStateService.pause()
        senderService.sendMessage(chatId, "Приостановлена обработка сообщений в групповом чате")
    }

    override fun getName(): String = "/pause"

    override fun needAdmin(): Boolean = true

    override fun getDescription(): String = "Приостанавливает обработку сообщений в групповом чате"

    override fun getFormat(): String = "/pause"

    override fun getUsage(): String = "/pause"


}