package com.plop.bankingkotlin.account.write.infrastructure.persistence

import com.plop.bankingkotlin.account.write.domain.Account
import com.plop.bankingkotlin.account.write.domain.AccountEventStore
import com.plop.bankingkotlin.account.write.domain.AccountHistory
import com.plop.bankingkotlin.account.write.domain.AccountId
import com.plop.bankingkotlin.buildingBlocks.DomainEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class InMemoryAccountEventStore: AccountEventStore {

    private val events: MutableMap<String, MutableList<DomainEvent>> = mutableMapOf()

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

    override fun getById(accountId: String): Account {
        val events = events[accountId]

        if (events == null) {
            throw PersistenceException.aggregateNotFound(accountId)
        }
        return Account.fromHistory(AccountHistory(accountId, events))
    }

    override fun store(domainEvent: DomainEvent) {
        var aggregateEvents = events[domainEvent.aggregateId]

        if (aggregateEvents == null) {
            aggregateEvents = mutableListOf()
            events[domainEvent.aggregateId] = aggregateEvents
        }

        aggregateEvents.add(domainEvent)
    }

}
