package com.plop.bankingkotlin.buildingBlocks

interface EventBusMiddleware {

    fun <E : DomainEvent> dispatch(event: E)

}
