package org.kitsuneko.vibehabrbot2025.config

import org.telegram.telegrambots.meta.api.methods.updates.GetUpdates
import java.util.function.Function

class CustomGenerator : Function<Int, GetUpdates> {

    private val allowedUpdates: MutableList<String> = ArrayList()

    override fun apply(lastReceivedUpdate: Int): GetUpdates {
        return GetUpdates
            .builder()
            .limit(GET_UPDATES_LIMIT)
            .timeout(GET_UPDATES_TIMEOUT)
            .offset(lastReceivedUpdate + 1)
            .allowedUpdates(allowedUpdates)
            .build()
    }

    companion object {
        private const val GET_UPDATES_LIMIT = 100
        private const val GET_UPDATES_TIMEOUT = 50
    }
}