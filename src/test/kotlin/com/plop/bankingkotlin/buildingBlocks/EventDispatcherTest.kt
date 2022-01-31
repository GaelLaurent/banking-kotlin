package com.plop.bankingkotlin.buildingBlocks

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EventDispatcherTest {

    @Test
    fun `should dispatch a event to the corresponding event handlers`() {

        // given
        val eventToDispatch = Event1("")
        val eventHandlerToCall = Event1Handler()
        val eventHandler2ToCall = Event1Handler2()
        val eventHandlerNotToCall = Event2Handler()
        val eventDispatcher = EventDispatcher(listOf(eventHandlerToCall, eventHandler2ToCall, eventHandlerNotToCall))

        // when
        eventDispatcher.dispatch(eventToDispatch)

        // then
        assertThat(eventHandlerToCall.eventDispatched).isEqualTo(eventToDispatch)
        assertThat(eventHandler2ToCall.eventDispatched).isEqualTo(eventToDispatch)
        assertThat(eventHandlerNotToCall.eventDispatched).isNull()
    }

}

class Event1Handler: EventHandler<Event1> {

    var eventDispatched: DomainEvent? = null

    override fun handle(event: Event1) {
        eventDispatched = event
    }

    override fun isAssignedTo() = Event1::class

}

class Event1Handler2: EventHandler<Event1> {

    var eventDispatched: DomainEvent? = null

    override fun handle(event: Event1) {
        eventDispatched = event
    }

    override fun isAssignedTo() = Event1::class

}

class Event1(override val id: String) : AggregateEvent()

class Event2Handler: EventHandler<Event2> {

    var eventDispatched: DomainEvent? = null

    override fun handle(event: Event2) {
        eventDispatched = event
    }

    override fun isAssignedTo() = Event2::class

}

class Event2(override val id: String) : AggregateEvent()

sealed class AggregateEvent : DomainEvent {
    override fun getAggregateId(): String = id

    abstract val id: String
}
