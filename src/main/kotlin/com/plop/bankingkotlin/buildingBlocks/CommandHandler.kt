package com.plop.bankingkotlin.buildingBlocks

import kotlin.reflect.KClass

interface CommandHandler<C: Command> {

    fun isAssignedTo(): KClass<C>

    fun handle(command: C): CommandResult

}
