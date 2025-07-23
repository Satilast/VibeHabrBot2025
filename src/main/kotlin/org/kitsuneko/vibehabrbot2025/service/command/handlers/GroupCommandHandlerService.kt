package org.kitsuneko.vibehabrbot2025.service.command.handlers

import org.kitsuneko.vibehabrbot2025.service.command.GroupCommand
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.message.Message
import java.util.function.Function
import java.util.stream.Collectors.toMap

@Service
class GroupCommandHandlerService(
    commands: List<GroupCommand>,
    private val permissionHandlerService: CommandPermissionHandlerService
) {

    private val commands: MutableMap<String, GroupCommand> = commands.stream()
        .collect(toMap(GroupCommand::getName, Function.identity()))

    fun handle(msg: Message) {
        if (!msg.text.startsWith("/")) return
        val args = msg.text.split(" ")
        val commandName = args[0]
        val command = commands[commandName]
        if (permissionHandlerService.isAllowed(msg, command ?: return)) command.execute(msg)
    }
}
