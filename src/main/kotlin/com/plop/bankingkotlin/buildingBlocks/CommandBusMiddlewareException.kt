package com.plop.bankingkotlin.buildingBlocks

class CommandBusMiddlewareException private constructor (override val message: String?): Exception(message) {

    companion object {
        fun commandHandlerNotFound(command: Command) = CommandBusMiddlewareException("Command handler not found for the command [${command::class}]")
    }

}

