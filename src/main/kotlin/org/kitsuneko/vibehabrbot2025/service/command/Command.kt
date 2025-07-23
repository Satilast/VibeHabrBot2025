package org.kitsuneko.vibehabrbot2025.service.command

import org.telegram.telegrambots.meta.api.objects.message.Message

interface Command {

    fun execute(message: Message)

    fun getName(): String

    fun needAdmin(): Boolean

    fun getDescription(): String

    fun getFormat(): String

    fun getUsage(): String
}