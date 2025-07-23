package org.kitsuneko.vibehabrbot2025.service.command.impl.privates

import org.kitsuneko.vibehabrbot2025.service.state.ScoreStateService
import org.kitsuneko.vibehabrbot2025.service.command.PrivateCommand
import org.kitsuneko.vibehabrbot2025.service.sender.SenderService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class ScoreCommand(
    private val stateService: ScoreStateService,
    private val senderService: SenderService
) : PrivateCommand {

    override fun execute(message: Message) {
        val chatId = message.chatId
        val sender = "@${message.from.userName}"
        senderService.sendMessage(chatId, "$sender: ${stateService.getScore(sender)} баллов")
    }

    override fun getName(): String = "/score"

    override fun needAdmin(): Boolean = false

    override fun getDescription(): String = "Выводит количество очков текущего пользователя"

    override fun getFormat(): String = "/score"

    override fun getUsage(): String = "/score"

}