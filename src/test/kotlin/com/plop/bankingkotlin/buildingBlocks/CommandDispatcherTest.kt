package com.plop.bankingkotlin.buildingBlocks

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

internal class CommandDispatcherTest {

    @Test
    fun `should dispatch a command to the corresponding command handler`() {

        // given
        val commandToDispatch = Command1()
        val commandHandlerToCall = Command1Handler()
        val commandHandlerNotToCall = Command2Handler()
        val commandDispatcher = CommandDispatcher(listOf(commandHandlerToCall, commandHandlerNotToCall))

        // when
        commandDispatcher.dispatch(commandToDispatch)

        // then
        assertThat(commandHandlerToCall.commandDispatched).isEqualTo(commandToDispatch)
        assertThat(commandHandlerNotToCall.commandDispatched).isNull()
    }

    @Test
    fun `should throw an error if there is no corresponding command handler`() {

        // given
        val commandToDispatch = Command1()
        val commandDispatcher = CommandDispatcher(listOf())

        // when
        // then
        assertThatExceptionOfType(CommandBusMiddlewareException::class.java).isThrownBy {
            commandDispatcher.dispatch(commandToDispatch)
        }.withMessage("Command handler not found for the command [${commandToDispatch::class}]")
    }

}

class Command2Handler : CommandHandler<Command2> {

    var commandDispatched: Command? = null

    override fun handle(command: Command2) {
        commandDispatched = command
    }

    override fun isAssignedTo() = Command2::class

}

class Command2: Command {

}

class Command1Handler: CommandHandler<Command1> {

    var commandDispatched: Command? = null

    override fun handle(command: Command1) {
        commandDispatched = command
    }

    override fun isAssignedTo() = Command1::class

}

class Command1: Command {

}


