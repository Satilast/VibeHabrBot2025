package org.kitsuneko.vibehabrbot2025.service.command.impl.privates

import org.kitsuneko.vibehabrbot2025.service.command.PrivateCommand
import org.kitsuneko.vibehabrbot2025.service.sender.SenderService
import org.kitsuneko.vibehabrbot2025.service.state.ContestParticipantsStateService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class SetAdminsCommand(
    private val participantsStateService: ContestParticipantsStateService,
    private val senderService: SenderService
) : PrivateCommand {

    override fun execute(message: Message) {
        val args = message.text.split(" ").toTypedArray()
        val users = args
            .sliceArray(1..args.lastIndex)
            .filter { it.startsWith("@") }
            .toTypedArray()

        val adminList = ArrayList<String>(listOf(*users))
        adminList.add("@" + message.from.userName)
        participantsStateService.setAdmins(adminList)

        notifyUser(message)
    }

    private fun notifyUser(message: Message) {
        val chatId = message.chatId
        val admins = participantsStateService.getAdmins()
        senderService.sendMessage(
            chatId,
            "Установлены администраторы со следующими именами пользователей: " + admins.joinToString(" ").trim()
        )
    }

    override fun getName(): String = "/admins"

    override fun needAdmin(): Boolean = true

    override fun getDescription(): String = """Назначает администраторов бота. 
        |Если никто не указан, то администратором назначается только текущий пользователь""".trimMargin()

    override fun getFormat(): String = "/admins [@user ...]"

    override fun getUsage(): String = """
        |/admins
        |/admins @romashka12 @bober76
    """.trimMargin()

}