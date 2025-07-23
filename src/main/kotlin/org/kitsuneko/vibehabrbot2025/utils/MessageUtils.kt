package org.kitsuneko.vibehabrbot2025.utils

import org.telegram.telegrambots.meta.api.objects.message.Message


object MessageUtils {
    fun isManualReply(message: Message?): Boolean {
        if (message == null || message.replyToMessage == null) {
            return false
        }

        val replyToId = message.replyToMessage.messageId
        val threadStartId = message.messageThreadId

        return threadStartId == null || threadStartId != replyToId
    }
}
