package org.kitsuneko.vibehabrbot2025.bot

import org.kitsuneko.vibehabrbot2025.service.UpdateDispatcherService
import org.springframework.beans.factory.InitializingBean

class BotInitializer(
    private val bot: ContestBot,
    private val dispatcher: UpdateDispatcherService
) : InitializingBean {

    override fun afterPropertiesSet() {
        bot.init(dispatcher)
    }
}