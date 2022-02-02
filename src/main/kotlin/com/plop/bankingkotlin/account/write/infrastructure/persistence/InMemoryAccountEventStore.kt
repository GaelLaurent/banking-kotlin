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

        try {

            val events = eventStreams.getValue(accountId)
            return AggregateHistory(accountId, events)

        } catch (e: NoSuchElementException) {
            throw PersistenceException.aggregateNotFound(accountId)
        }
    }

    override fun store(domainEvent: AccountEvent) {
        val aggregateId = domainEvent.getAggregateId()

        val eventStream = eventStreams.getOrPut(aggregateId, this::createNewEventStream)

        eventStream.add(domainEvent)
    }

    private fun createNewEventStream(): MutableList<AccountEvent> = mutableListOf()

}
