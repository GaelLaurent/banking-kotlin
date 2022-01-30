package com.plop.bankingkotlin.buildingBlocks

import kotlin.reflect.KClass

class CommandDispatcher: CommandBusMiddleware {

    private var registeredCommandHandlers: Map<KClass<out Command>, CommandHandler<*>> = mapOf()

    constructor(commandHandlers: List<CommandHandler<*>>) {

        registeredCommandHandlers = commandHandlers.associateBy { it.isAssignedTo() }

    }

    override fun <C : Command> dispatch(command: C) {

        if (registeredCommandHandlers[command::class] == null) {
            throw CommandBusMiddlewareException.commandHandlerNotFound(command)
        }

        @Suppress("UNCHECKED_CAST")
        val commandHandler = registeredCommandHandlers[command::class] as CommandHandler<C>

        commandHandler.handle(command)

    }

}
