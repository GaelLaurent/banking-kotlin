package com.plop.bankingkotlin.buildingBlocks

interface EventStore<E: DomainEvent> {

    fun nextId(): String

    fun getById(accountId: String): AggregateHistory<E>

    fun store(domainEvent: E)

}
