package org.kitsuneko.vibehabrbot2025.dto

data class ScoreStateDto(
    val scores: Map<String, Int>,
    val answered: Map<Int, MutableSet<Long>>
)
