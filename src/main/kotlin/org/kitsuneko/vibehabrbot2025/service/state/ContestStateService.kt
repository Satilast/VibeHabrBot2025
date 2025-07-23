package org.kitsuneko.vibehabrbot2025.service.state

import org.kitsuneko.vibehabrbot2025.model.ContestState
import org.springframework.stereotype.Service

@Service
class ContestStateService {

    private val state = ContestState()

    fun setContestState(targetGroupId: Long, chatId: Long, threadId: Int) =
        state.setContestState(targetGroupId, chatId, threadId)

    fun getContestState(): ContestState = state

}