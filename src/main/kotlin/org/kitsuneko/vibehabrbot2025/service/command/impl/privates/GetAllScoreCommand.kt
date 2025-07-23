package org.kitsuneko.vibehabrbot2025.service.command.impl.privates

import org.kitsuneko.vibehabrbot2025.service.state.ScoreStateService
import org.kitsuneko.vibehabrbot2025.service.command.PrivateCommand
import org.kitsuneko.vibehabrbot2025.service.file.FileService
import org.kitsuneko.vibehabrbot2025.service.sender.SenderService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class GetAllScoreCommand(
    private val stateService: ScoreStateService,
    private val senderService: SenderService,
    private val fileService: FileService
) : PrivateCommand {

    override fun execute(message: Message) {
        val chatId = message.chatId
        val list = stateService.getAllScores().entries
            .sortedByDescending { it.value }
            .joinToString("\n") { "${it.key}: ${it.value} баллов" }

        list.ifEmpty {
            senderService.sendMessage(chatId, "Нет данных")
            return
        }

        val file = fileService.createInputFileFromString("Список участников с баллами.txt", list)
        senderService.sendDocument(chatId, "Данные об участниках сохранены и получены", file)
    }

    override fun getName(): String = "/all"

    override fun needAdmin(): Boolean = true

    override fun getDescription(): String = "Присылает файл со списоком всех пользователей, получивших очки"

    override fun getFormat(): String = "/all"

    override fun getUsage(): String = "/all"

}