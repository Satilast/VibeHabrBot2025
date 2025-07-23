package org.kitsuneko.vibehabrbot2025.service.command.impl.privates

import org.kitsuneko.vibehabrbot2025.service.command.PrivateCommand
import org.kitsuneko.vibehabrbot2025.service.sender.SenderService
import org.kitsuneko.vibehabrbot2025.service.state.ContestParticipantsStateService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class SetScoreIgnoredCommand(
    private val participantsStateService: ContestParticipantsStateService,
    private val senderService: SenderService
) : PrivateCommand {

    override fun execute(message: Message) {
        val chatId = message.chatId
        val args = message.text.split(" ").toTypedArray()
        if (args.size == 1) {
            participantsStateService.setScoreIgnored(listOf())
            senderService.sendMessage(chatId, "Список игнорируемых участников при подсчёте очков очищен")
            return
        }
        val users = args
            .sliceArray(1..args.lastIndex)
            .filter { it.startsWith("@") }
            .toTypedArray()
        participantsStateService.setScoreIgnored(listOf(*users))
        val ignored = participantsStateService.getScoreIgnored()
        senderService.sendMessage(
            chatId, "Установлены игнорируемые при подсчёте очков участники со следующими именами пользователей: "
                    + ignored.joinToString(" ").trim()
        )
    }

    override fun getName(): String = "/ignored"

    override fun needAdmin(): Boolean = true

    override fun getDescription(): String =
        """Устанавливает пользователей, которые будут игнорироваться при подсчёте очков
            |Если никто не указан, то список очищается""".trimMargin()

    override fun getFormat(): String = "/ignored [@user ...]"

    override fun getUsage(): String = """/ignored @adminka777 @nicegirl
        |/ignored""".trimMargin()

}