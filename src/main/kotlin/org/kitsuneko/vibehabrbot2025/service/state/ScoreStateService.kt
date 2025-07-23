package org.kitsuneko.vibehabrbot2025.service.state

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.kitsuneko.vibehabrbot2025.dto.ScoreStateDto
import org.kitsuneko.vibehabrbot2025.model.ScoreState
import org.kitsuneko.vibehabrbot2025.service.file.AppDirectoryService
import org.springframework.stereotype.Service

@Service
class ScoreStateService(
    appDirectoryService: AppDirectoryService,
    private val participantsStateService: ContestParticipantsStateService
) {

    private val file = appDirectoryService.getFileInAppDirectory("state.json")
    private val mapper = jacksonObjectMapper()

    private var state = ScoreState()

    init {
        load()
    }

    fun adjust(delta: Int, vararg users: String) {
        val scoreIgnored = participantsStateService.getScoreIgnored()
        state.adjustScore(delta, scoreIgnored, *users)
    }

    fun hasAnswered(cardId: Int, userId: Long): Boolean = state.hasAnswered(cardId, userId)

    fun markAnswered(cardId: Int, userId: Long) = state.markAnswered(cardId, userId)

    fun getScore(user: String): Int = state.getAllScore(user)

    fun getAllScores(): Map<String, Int> = state.getScores()

    fun save() = file.writer().use { it.write(mapper.writeValueAsString(state.toDto())) }

    private fun load() {
        if (file.exists()) {
            file.reader().use {
                val dto = mapper.readValue(it, ScoreStateDto::class.java)
                state.setAllFromDto(dto)
            }
        }
    }
}