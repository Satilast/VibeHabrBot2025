package org.kitsuneko.vibehabrbot2025.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "telegram.bot")
class TelegramBotProperties {
    var username: String = ""
    var token: String = ""
}