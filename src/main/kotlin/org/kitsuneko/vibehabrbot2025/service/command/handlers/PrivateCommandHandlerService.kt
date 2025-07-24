package org.kitsuneko.vibehabrbot2025.service.command.handlers

import org.kitsuneko.vibehabrbot2025.service.command.PrivateCommand
import org.kitsuneko.vibehabrbot2025.service.sender.SenderService
import org.kitsuneko.vibehabrbot2025.service.state.BotStateService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.message.Message
import java.util.function.Function
import java.util.stream.Collectors.toMap

@Service
class PrivateCommandHandlerService(
    commands: List<PrivateCommand>,
    private val botStateService: BotStateService,
    private val permissionHandlerService: CommandPermissionHandlerService,
    private val senderService: SenderService
) {

    private val commands: MutableMap<String, PrivateCommand> = commands.stream()
        .collect(toMap(PrivateCommand::getName, Function.identity()))

    fun handle(msg: Message) {
        val chatId = msg.chatId
        if (!botStateService.isStarted()) {
            senderService.sendMessage(chatId, "Бот не запущен. Для запуска бота выполните команду /start")
            return
        }
        if (msg.text == null || !msg.text.startsWith("/")) return
        val args = msg.text.split(" ")
        val commandName = args[0]
        val command = commands[commandName]
        if (permissionHandlerService.isAllowed(msg, command ?: return)) {
            command.execute(msg)
        } else {
            senderService.sendMessage(chatId, "У Вас недостаточно прав на выполнение данной команды")
        }
    }
}