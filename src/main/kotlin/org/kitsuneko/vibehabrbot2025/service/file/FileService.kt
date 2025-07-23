package org.kitsuneko.vibehabrbot2025.service.file

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.InputFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

@Service
class FileService {

    fun createInputFileFromString(filename:String, data: String): InputFile {
        val byteOutputStream = ByteArrayOutputStream()
        byteOutputStream.write(data.toByteArray())

        val bytes = byteOutputStream.toByteArray()
        val byteInputStream = ByteArrayInputStream(bytes)
        return InputFile(byteInputStream, filename)
    }
}