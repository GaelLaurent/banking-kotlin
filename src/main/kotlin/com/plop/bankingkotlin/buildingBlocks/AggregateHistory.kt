package com.plop.bankingkotlin.buildingBlocks

data class AggregateHistory<E: DomainEvent>(val accountId: String, val events: List<E>)
