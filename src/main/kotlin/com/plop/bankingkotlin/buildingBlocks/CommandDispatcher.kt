package com.plop.bankingkotlin.buildingBlocks

import kotlin.reflect.KClass

class CommandDispatcher(commandHandlers: List<CommandHandler<*>>) : CommandBusMiddleware {

    private var registeredCommandHandlers: Map<KClass<out Command>, CommandHandler<*>> = mapOf()

    init {
        registeredCommandHandlers = commandHandlers.associateBy { it.isAssignedTo() }
    }

    override fun <C : Command> dispatch(command: C) {

        if (isHandled(command)) {
            throw CommandBusMiddlewareException.commandHandlerNotFound(command)
        }

        @Suppress("UNCHECKED_CAST")
        val commandHandler = registeredCommandHandlers[command::class] as CommandHandler<C>

        commandHandler.handle(command)

    }

    private fun <C : Command> isHandled(command: C) = registeredCommandHandlers[command::class] == null

}
