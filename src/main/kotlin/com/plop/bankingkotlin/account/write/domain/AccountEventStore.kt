package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.DomainEvent

interface AccountEventStore {
    fun nextId(): String

    fun getById(accountId: String): AccountHistory

    fun store(domainEvent: DomainEvent)

}
