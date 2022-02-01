package com.plop.bankingkotlin.account.write.infrastructure.persistence

import com.plop.bankingkotlin.account.write.domain.AccountEvent
import com.plop.bankingkotlin.account.write.domain.AccountEventStore
import com.plop.bankingkotlin.buildingBlocks.AggregateHistory
import org.springframework.stereotype.Component
import java.util.*

@Component
class InMemoryAccountEventStore: AccountEventStore {

    private val eventStreams: MutableMap<String, MutableList<AccountEvent>> = mutableMapOf()

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

    override fun getById(accountId: String): AggregateHistory<AccountEvent> {
        val events = eventStreams[accountId]

        if (events == null) {
            throw PersistenceException.aggregateNotFound(accountId)
        }
        return AggregateHistory(accountId, events)
    }

    override fun store(domainEvent: AccountEvent) {
        var eventStream = eventStreams[domainEvent.getAggregateId()]

        if (eventStream == null) {
            eventStream = createNewEventStream()
            addEventStream(domainEvent, eventStream)
        }

        eventStream.add(domainEvent)
    }

    private fun addEventStream(domainEvent: AccountEvent, aggregateEvents: MutableList<AccountEvent>) {
        eventStreams[domainEvent.getAggregateId()] = aggregateEvents
    }

    private fun createNewEventStream(): MutableList<AccountEvent> = mutableListOf()

}
