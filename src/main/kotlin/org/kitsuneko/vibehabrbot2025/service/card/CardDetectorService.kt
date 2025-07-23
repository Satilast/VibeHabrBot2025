package org.kitsuneko.vibehabrbot2025.service.card

import org.kitsuneko.vibehabrbot2025.service.card.impl.CardOne
import org.kitsuneko.vibehabrbot2025.service.card.impl.CardThree
import org.kitsuneko.vibehabrbot2025.service.card.impl.CardTwo
import org.kitsuneko.vibehabrbot2025.service.state.ContestParticipantsStateService
import org.kitsuneko.vibehabrbot2025.service.state.ScoreStateService
import org.springframework.stereotype.Service

@Service
class CardDetectorService(
    private val stateService: ScoreStateService,
    private val participantsStateService: ContestParticipantsStateService
) {
    fun getCard(text: String): Card? = when {
        "Это карточка вопроса." in text -> CardOne(stateService)
        "Отправьте его никнейм через @" in text -> CardThree(stateService, participantsStateService)
        "Это карточка действия." in text -> CardTwo(stateService)
        else -> null
    }
}