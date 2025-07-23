package org.kitsuneko.vibehabrbot2025.service.card.impl

import org.kitsuneko.vibehabrbot2025.service.state.ScoreStateService
import org.kitsuneko.vibehabrbot2025.service.card.Card

class CardTwo(stateService: ScoreStateService) : Card(stateService) {

    override fun getPoints(): Int = 2

}