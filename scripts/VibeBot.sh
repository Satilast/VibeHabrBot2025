#!/bin/bash
# Скрипт запуска бота

# Путь к JAR-файлу
JAR_NAME="VibeHabrBot2025-1.0.0.jar"

# Запуск с проверкой наличия файла
if [ -f "$JAR_NAME" ]; then
    echo "Запускаю бота..."
    java -jar "$JAR_NAME" --telegram.bot.token=yourtokenhere
else
    echo "Ошибка: файл $JAR_NAME не найден!"
fi