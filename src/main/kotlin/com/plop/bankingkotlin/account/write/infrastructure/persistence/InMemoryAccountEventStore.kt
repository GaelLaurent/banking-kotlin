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

        if (events.isNullOrEmpty()) {
            throw PersistenceException.aggregateNotFound(accountId)
        }

        return AggregateHistory(accountId, events)
    }

    override fun store(domainEvent: AccountEvent) {
        val aggregateId = domainEvent.getAggregateId()

        val eventStream = eventStreams.getOrPut(aggregateId, this::createNewEventStream)

        eventStream.add(domainEvent)
    }

    private fun createNewEventStream(): MutableList<AccountEvent> = mutableListOf()

}
