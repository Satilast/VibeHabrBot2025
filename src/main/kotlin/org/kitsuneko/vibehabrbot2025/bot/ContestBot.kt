package org.kitsuneko.vibehabrbot2025.bot

import org.kitsuneko.vibehabrbot2025.config.properties.TelegramBotProperties
import org.kitsuneko.vibehabrbot2025.service.UpdateDispatcherService
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.objects.Update

class ContestBot(
    private val props: TelegramBotProperties
) : SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private lateinit var dispatcher: UpdateDispatcherService

    fun init(dispatcher: UpdateDispatcherService) {
        this.dispatcher = dispatcher
    }

    override fun getBotToken(): String = props.token

    override fun getUpdatesConsumer(): LongPollingUpdateConsumer = this

    override fun consume(update: Update) {
        if (::dispatcher.isInitialized) {
            dispatcher.dispatch(update)
        }
    }

}