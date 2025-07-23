package org.kitsuneko.vibehabrbot2025.service.command.impl.privates

import org.kitsuneko.vibehabrbot2025.service.report.HelpReportService
import org.kitsuneko.vibehabrbot2025.service.command.PrivateCommand
import org.kitsuneko.vibehabrbot2025.service.sender.SenderService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class HelpCommand(
    private val helpReportService: HelpReportService,
    private val senderService: SenderService
) : PrivateCommand {

    override fun execute(message: Message) {
        val chatId = message.chatId
        val report = helpReportService.getReport(message)
        senderService.sendMessage(chatId, report)
    }

    override fun getName(): String = "/help"

    override fun needAdmin(): Boolean = false

    override fun getDescription(): String = "Выводит список доступных команд"

    override fun getFormat(): String = "/help"

    override fun getUsage(): String = "/help"

}