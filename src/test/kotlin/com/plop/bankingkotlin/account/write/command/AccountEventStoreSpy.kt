package com.plop.bankingkotlin.account.write.command

import com.plop.bankingkotlin.account.write.domain.AccountEventStore
import com.plop.bankingkotlin.account.write.domain.AccountHistory
import com.plop.bankingkotlin.buildingBlocks.DomainEvent

class AccountEventStoreSpy(private val eventStore: AccountEventStore) : AccountEventStore {

    private val eventsStored = mutableSetOf<DomainEvent>()

    override fun nextId(): String {
        return eventStore.nextId()
    }

    override fun getById(accountId: String): AccountHistory {
        return eventStore.getById(accountId)
    }

    override fun store(domainEvent: DomainEvent) {
        eventsStored.add(domainEvent)
        eventStore.store(domainEvent)
    }

    fun getEventsStored() = eventsStored

}
