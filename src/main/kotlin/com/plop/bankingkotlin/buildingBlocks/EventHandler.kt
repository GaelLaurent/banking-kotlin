package com.plop.bankingkotlin.buildingBlocks

import kotlin.reflect.KClass

interface EventHandler<E: DomainEvent> {

    fun isAssignedTo(): KClass<E>

    fun handle(event: E)

}
