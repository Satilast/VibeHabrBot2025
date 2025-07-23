package org.kitsuneko.vibehabrbot2025.service.sender

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.generics.TelegramClient


@Service
class SenderService(val client: TelegramClient) {

    fun sendMessage(chatId: Long, text: String) {
        try {
            client.execute(SendMessage(chatId.toString(), text))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendDocument(chatId: Long, caption: String, file: InputFile) {
        try {
            val sendDocument = SendDocument(chatId.toString(), file)
            sendDocument.caption = caption
            client.execute(sendDocument)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}