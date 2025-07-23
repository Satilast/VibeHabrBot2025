package org.kitsuneko.vibehabrbot2025.model

import org.kitsuneko.vibehabrbot2025.dto.ScoreStateDto

class ScoreState {
    private val scores: MutableMap<String, Int> = mutableMapOf()
    private val answered: MutableMap<Int, MutableSet<Long>> = mutableMapOf()

    fun adjustScore(delta: Int, ignored: List<String>, vararg users: String) {
        for (user in users) {
            if (user in ignored) continue
            if (!user.startsWith("@")) continue
            val score = scores[user]
            if (delta < 0 && score != null && score + delta <= 0) {
                scores.remove(user)
            } else {
                scores.merge(user, delta, Int::plus)
            }
        }
    }

    fun hasAnswered(cardId: Int, userId: Long): Boolean = answered[cardId]?.contains(userId) == true

    fun markAnswered(cardId: Int, userId: Long) = answered.computeIfAbsent(cardId) { mutableSetOf() }.add(userId)

    fun getAllScore(user: String): Int = scores.getOrDefault(user, 0)

    fun getScores(): Map<String, Int> = scores.toSortedMap()

    fun toDto(): ScoreStateDto = ScoreStateDto(scores.toSortedMap(), answered.toSortedMap())

    fun setAllFromDto(dto: ScoreStateDto) {
        scores.putAll(dto.scores)
        answered.putAll(dto.answered)
    }
}