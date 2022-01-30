package com.plop.bankingkotlin.buildingBlocks

interface CommandBusMiddleware {

    fun <C : Command> dispatch(command: C)

}
