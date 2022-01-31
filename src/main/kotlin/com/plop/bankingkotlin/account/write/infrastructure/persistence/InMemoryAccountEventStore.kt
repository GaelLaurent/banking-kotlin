package com.plop.bankingkotlin.account.write.infrastructure.persistence

import com.plop.bankingkotlin.account.write.domain.AccountEvent
import com.plop.bankingkotlin.account.write.domain.AccountEventStore
import com.plop.bankingkotlin.buildingBlocks.AggregateHistory
import org.springframework.stereotype.Component
import java.util.*

@Component
class InMemoryAccountEventStore: AccountEventStore {

    private val events: MutableMap<String, MutableList<AccountEvent>> = mutableMapOf()

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

    override fun getById(accountId: String): AggregateHistory<AccountEvent> {
        val events = events[accountId]

        if (events == null) {
            throw PersistenceException.aggregateNotFound(accountId)
        }
        return AggregateHistory(accountId, events)
    }

    override fun store(domainEvent: AccountEvent) {
        var aggregateEvents = events[domainEvent.getAggregateId()]

        if (aggregateEvents == null) {
            aggregateEvents = mutableListOf()
            events[domainEvent.getAggregateId()] = aggregateEvents
        }

        aggregateEvents.add(domainEvent)
    }

}
