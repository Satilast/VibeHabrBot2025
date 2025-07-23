package org.kitsuneko.vibehabrbot2025.service.command.impl.privates

import org.kitsuneko.vibehabrbot2025.service.command.PrivateCommand
import org.kitsuneko.vibehabrbot2025.service.state.ScoreStateService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.message.Message

@Component
class AdjustCommand(
    private val stateService: ScoreStateService
) : PrivateCommand {

    override fun execute(message: Message) {
        val args = message.text.split(" ").toTypedArray()
        val users = args.sliceArray(1..<args.lastIndex)
        stateService.adjust(args[args.lastIndex].toInt(), *users)
        stateService.save()
    }

    override fun getName(): String = "/adjust"

    override fun needAdmin(): Boolean = true

    override fun getDescription(): String =
        "Прибавляет или убавляет (в зависимости от знака) количество очков пользователю (или нескольким пользователям)"

    override fun getFormat(): String = "/adjust <@user1> [@user2 ...] <изменение очков>"

    override fun getUsage(): String = """
        |/adjust @romashka12 @bober76 -2
        |/adjust @happynick 5
    """.trimMargin()

}