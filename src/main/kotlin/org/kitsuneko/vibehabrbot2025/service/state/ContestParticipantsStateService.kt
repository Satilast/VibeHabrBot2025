package org.kitsuneko.vibehabrbot2025.service.state

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.kitsuneko.vibehabrbot2025.model.ContestParticipantsState
import org.kitsuneko.vibehabrbot2025.service.file.AppDirectoryService
import org.springframework.stereotype.Service

@Service
class ContestParticipantsStateService(
    appDirectoryService: AppDirectoryService
) {
    private val file = appDirectoryService.getFileInAppDirectory("excluded.json")
    private val mapper = jacksonObjectMapper()

    private var state = ContestParticipantsState()

    fun setAdmins(admins: List<String>) {
        state.setAdmins(admins)
        save()
    }

    fun setScoreIgnored(scoreIgnored: List<String>) {
        state.setScoreIgnored(scoreIgnored)
        save()
    }

    fun getAdmins(): List<String> {
        if (state.getAdmins().isEmpty()) {
            state = load()
        }
        return state.getAdmins()
    }

    fun isAdmin(adminName: String): Boolean {
        return state.getAdmins().contains(adminName)
    }

    fun getScoreIgnored(): List<String> {
        if (state.getScoreIgnored().isEmpty()) {
            state = load()
        }
        return state.getScoreIgnored()
    }

    private fun save() {
        mapper.writeValue(file, state)
    }

    private fun load(): ContestParticipantsState {
        if (file.exists()) {
            try {
                state = ObjectMapper().readValue(file, ContestParticipantsState::class.java)
            } catch (e: Exception) {
                println("Ошибка чтения admins.json: $e")
            }
        }
        return state
    }
}