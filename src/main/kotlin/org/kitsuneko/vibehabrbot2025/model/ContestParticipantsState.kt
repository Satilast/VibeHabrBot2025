package org.kitsuneko.vibehabrbot2025.model

class ContestParticipantsState {
    private val admins: ArrayList<String> = arrayListOf()
    private val scoreIgnored: ArrayList<String> = arrayListOf()

    fun setAdmins(newAdmins: List<String>) {
        admins.clear()
        admins.addAll(newAdmins)
    }

    fun getAdmins(): List<String> = admins

    fun setScoreIgnored(newScoreIgnored: List<String>) {
        scoreIgnored.clear()
        scoreIgnored.addAll(newScoreIgnored)
    }

    fun getScoreIgnored(): List<String> = scoreIgnored
}
