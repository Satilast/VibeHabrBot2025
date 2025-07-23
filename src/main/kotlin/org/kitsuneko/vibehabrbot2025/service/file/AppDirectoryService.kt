package org.kitsuneko.vibehabrbot2025.service.file

import org.springframework.stereotype.Service
import java.io.File
import java.net.URLDecoder

@Service
class AppDirectoryService {

    private val baseDir: File by lazy {
        val path = resolveJarLocation()
        File(path)
    }

    private fun resolveJarLocation(): String {
        val uri = AppDirectoryService::class.java.protectionDomain
            .codeSource?.location ?: error("Cannot resolve code source")

        val raw = uri.toString() // может быть: jar:file:/C:/..., или nested:/C:/...

        return when {
            raw.startsWith("file:/") && raw.endsWith(".jar") -> {
                // обычный jar, неупакованный
                File(URLDecoder.decode(raw.removePrefix("file:/"), "UTF-8")).parent
            }

            raw.startsWith("jar:file:/") -> {
                // Spring Boot fat jar
                val cleaned = raw.removePrefix("jar:file:/")
                val jarIndex = cleaned.indexOf(".jar")
                val jarPath = cleaned.substring(0, jarIndex + 4)
                File(URLDecoder.decode("/$jarPath", "UTF-8")).parent
            }

            raw.startsWith("nested:/") -> {
                // НОВЫЙ формат spring-boot 3.2+ (jar:nested:/C:/...)
                val cleaned = raw.removePrefix("nested:/")
                val jarIndex = cleaned.indexOf(".jar")
                val jarPath = cleaned.substring(0, jarIndex + 4)
                File(URLDecoder.decode(jarPath, "UTF-8")).parent
            }

            else -> {
                // fallback — запуск из IDE
                File(".").canonicalPath
            }
        }
    }

    fun getFileInAppDirectory(filename: String): File = File(baseDir, filename)

}