package org.kitsuneko.vibehabrbot2025.service.card

import org.kitsuneko.vibehabrbot2025.service.state.ScoreStateService
import org.telegram.telegrambots.meta.api.objects.message.Message

abstract class Card(val stateService: ScoreStateService) {

    open fun handle(msg: Message) {
        val userId = msg.from.id
        val userName = "@${msg.from.userName}"
        val replyId = msg.replyToMessage.messageId

        stateService.adjust(getPoints(), userName)
        stateService.markAnswered(replyId, userId)
        stateService.save()
    }

    abstract fun getPoints(): Int

}