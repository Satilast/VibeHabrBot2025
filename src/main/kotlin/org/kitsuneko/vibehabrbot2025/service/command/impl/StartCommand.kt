package org.kitsuneko.vibehabrbot2025.service.command.impl

import org.kitsuneko.vibehabrbot2025.service.InitializerService
import org.kitsuneko.vibehabrbot2025.service.command.Command
import org.kitsuneko.vibehabrbot2025.service.sender.SenderService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class StartCommand(
    private val senderService: SenderService,
    private val initializerService: InitializerService
) : Command {

    override fun execute(message: Message) {
        val chatId = message.chatId
        val username = "@" + message.from.userName

        if (initializerService.initialize(username)) {
            senderService.sendMessage(chatId, "Бот успешно запущен.\nДля получения справки по командам выполните команду /help")
        } else {
            senderService.sendMessage(chatId, "Бот уже был запущен ранее")
        }
    }

    override fun getName(): String = "/start"

    override fun needAdmin(): Boolean = true

    override fun getDescription(): String = "Запускает бота, устанавливая текущего пользователя как администратора"

    override fun getFormat(): String = "/start"

    override fun getUsage(): String = "/start"

}