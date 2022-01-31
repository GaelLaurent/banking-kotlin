package com.plop.bankingkotlin.buildingBlocks

interface DomainEvent {
    fun getAggregateId(): String
}
