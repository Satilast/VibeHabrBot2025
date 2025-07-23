package org.kitsuneko.vibehabrbot2025.service.card.impl

import org.kitsuneko.vibehabrbot2025.service.card.Card
import org.kitsuneko.vibehabrbot2025.service.state.ContestParticipantsStateService
import org.kitsuneko.vibehabrbot2025.service.state.ScoreStateService
import org.telegram.telegrambots.meta.api.objects.message.Message

class CardThree(
    stateService: ScoreStateService,
    private val participantsStateService: ContestParticipantsStateService
) : Card(stateService) {

    override fun handle(msg: Message) {
        val entities = msg.entities
        if (entities?.count { it.type == "mention" } != 1) {
            return
        }
        entities
            .filter { it.type == "mention" }
            .map { msg.text.substring(it.offset, it.offset + it.length) }
            .filter { participantsStateService.getScoreIgnored().contains(it) }
            .forEach { mention -> addPoints(mention, msg) }

    }

    private fun addPoints(mention: String, msg: Message) {
        val userName = "@${msg.from.userName}"
        if (mention == userName || participantsStateService.getScoreIgnored().contains(userName)) return

        val userId = msg.from.id
        val replyId = msg.replyToMessage.messageId

        stateService.adjust(getPoints(), mention)
        stateService.adjust(getPoints(), userName)
        stateService.markAnswered(replyId, userId)
        stateService.save()
    }

    override fun getPoints(): Int = 1

}