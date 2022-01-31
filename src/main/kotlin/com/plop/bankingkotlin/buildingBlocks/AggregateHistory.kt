package com.plop.bankingkotlin.buildingBlocks

data class AggregateHistory<E: DomainEvent>(val aggregateId: String, val events: List<E>)
