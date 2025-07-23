package org.kitsuneko.vibehabrbot2025.service.message

import org.kitsuneko.vibehabrbot2025.service.card.CardDetectorService
import org.kitsuneko.vibehabrbot2025.service.command.handlers.GroupCommandHandlerService
import org.kitsuneko.vibehabrbot2025.service.state.BotStateService
import org.kitsuneko.vibehabrbot2025.service.state.ContestStateService
import org.kitsuneko.vibehabrbot2025.service.state.ScoreStateService
import org.kitsuneko.vibehabrbot2025.utils.MessageUtils.isManualReply
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.message.Message

@Service
class GroupMessageHandlerService(
    private val stateService: ScoreStateService,
    private val contestStateService: ContestStateService,
    private val botStateService: BotStateService,
    private val detector: CardDetectorService,
    private val commandHandlerService: GroupCommandHandlerService,
) {

    fun handle(msg: Message) {
        if (!botStateService.isStarted()) return
        commandHandlerService.handle(msg)
        if (botStateService.isPaused()) return
        val contestState = contestStateService.getContestState()
        if (msg.chatId != contestState.chatId) return
        if (msg.messageThreadId != contestState.threadId) return
        if (!isManualReply(msg)) return

        val replyTo = msg.replyToMessage ?: return
        if (replyTo.from.id != contestState.targetGroupId) return
        val card = detector.getCard(replyTo.text ?: replyTo.caption ?: return) ?: return

        val userId = msg.from.id
        val replyId = replyTo.messageId
        if (stateService.hasAnswered(replyId, userId)) return

        card.handle(msg)
    }

}