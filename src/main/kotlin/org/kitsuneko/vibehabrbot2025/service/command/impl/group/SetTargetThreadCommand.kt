package org.kitsuneko.vibehabrbot2025.service.command.impl.group

import org.kitsuneko.vibehabrbot2025.service.command.GroupCommand
import org.kitsuneko.vibehabrbot2025.service.state.ContestStateService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class SetTargetThreadCommand(
    private val contestStateService: ContestStateService
) : GroupCommand {

    override fun execute(message: Message) {
        val chatId = message.chatId
        val threadId = message.messageThreadId?: return
        val targetGroupId = message.from.id
        contestStateService.setContestState(targetGroupId, chatId, threadId)
    }

    override fun getName(): String = "/thread"

    override fun needAdmin(): Boolean = true

    override fun getDescription(): String =
        """⚠️Работает только в групповом чате и в существующей ветке!
            |Устанавливает текущий групповой чат и текущую ветку группы для подсчёта очков в ней.
            |Очки будут назначаться только за ответы на сообщения текущего пользователя (инициировавшего команду)
        """.trimMargin()

    override fun getFormat(): String = "/thread"

    override fun getUsage(): String = "/thread"

}