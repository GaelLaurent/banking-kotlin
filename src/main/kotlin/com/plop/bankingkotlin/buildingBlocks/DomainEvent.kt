package com.plop.bankingkotlin.buildingBlocks

interface DomainEvent {
    val aggregateId: String
}

class NothingHappened(override val aggregateId: String) : DomainEvent
