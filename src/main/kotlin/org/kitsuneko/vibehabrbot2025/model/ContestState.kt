package org.kitsuneko.vibehabrbot2025.model

class ContestState {
    private var _targetGroupId: Long = 0
    private var _chatId: Long = 0
    private var _threadId: Int = 0

    val targetGroupId: Long get() = _targetGroupId
    val chatId: Long get() = _chatId
    val threadId: Int get() = _threadId

    fun setContestState(targetGroupId: Long, chatId: Long, threadId: Int) {
        this._targetGroupId = targetGroupId
        this._chatId = chatId
        this._threadId = threadId
    }


}