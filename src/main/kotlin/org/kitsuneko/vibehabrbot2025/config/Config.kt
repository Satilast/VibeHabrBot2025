package org.kitsuneko.vibehabrbot2025.config

import org.kitsuneko.vibehabrbot2025.bot.BotInitializer
import org.kitsuneko.vibehabrbot2025.bot.ContestBot
import org.kitsuneko.vibehabrbot2025.config.properties.TelegramBotProperties
import org.kitsuneko.vibehabrbot2025.service.UpdateDispatcherService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication
import org.telegram.telegrambots.meta.TelegramUrl.DEFAULT_URL
import org.telegram.telegrambots.meta.generics.TelegramClient


@Configuration
class Config {

    @Bean
    fun client(props: TelegramBotProperties): TelegramClient {
        return OkHttpTelegramClient(props.token)
    }

    @Bean
    fun contestBot(props: TelegramBotProperties): ContestBot = ContestBot(props)

    @Bean
    fun botInitializer(
        bot: ContestBot, dispatcher: UpdateDispatcherService
    ): BotInitializer {
        return BotInitializer(bot, dispatcher)
    }

    @Bean
    fun application(bot: ContestBot): TelegramBotsLongPollingApplication {
        val app = TelegramBotsLongPollingApplication()
        app.registerBot(bot.botToken, { DEFAULT_URL }, CustomGenerator(), bot.updatesConsumer)
        return app
    }

}