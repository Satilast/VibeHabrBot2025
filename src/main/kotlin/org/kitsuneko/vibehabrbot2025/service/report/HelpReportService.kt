package org.kitsuneko.vibehabrbot2025.service.report

import org.kitsuneko.vibehabrbot2025.service.command.Command
import org.kitsuneko.vibehabrbot2025.service.state.ContestParticipantsStateService
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.message.Message

@Service
class HelpReportService(
    private val commands: ObjectProvider<List<Command>>,
    private val participantsStateService: ContestParticipantsStateService
) {

    fun getReport(message: Message): String {
        var commands = commands.ifAvailable ?: return ""
        val username = "@" + message.from.userName

        if (!participantsStateService.isAdmin(username)) {
            commands = commands.filter { !it.needAdmin() }.toMutableList()
        }
        return buildString {
            appendLine("\uD83D\uDCDAСправка по доступным командам")
            appendLine()
            commands.forEach { cmd ->
                appendLine("📘 ${cmd.getName()}")
                appendLine(cmd.getDescription().trim())

                val format = cmd.getFormat().trim()
                if (format.isNotBlank()) {
                    appendLine("Формат: `$format`")
                }

                val usage = cmd.getUsage().trim()
                if (usage.isNotBlank()) {
                    appendLine("Пример: `$usage`")
                }

                appendLine()
            }
        }
    }

}