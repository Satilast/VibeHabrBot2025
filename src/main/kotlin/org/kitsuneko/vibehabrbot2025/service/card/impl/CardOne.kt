package org.kitsuneko.vibehabrbot2025.service.card.impl

import org.kitsuneko.vibehabrbot2025.service.state.ScoreStateService
import org.kitsuneko.vibehabrbot2025.service.card.Card

class CardOne(stateService: ScoreStateService) : Card(stateService) {

    override fun getPoints(): Int = 1

}