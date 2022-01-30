package com.plop.bankingkotlin.buildingBlocks

import kotlin.reflect.KClass

class EventDispatcher: EventBusMiddleware {

    private var registeredCommandHandlers: Map<KClass<out DomainEvent>, List<EventHandler<out DomainEvent>>> = mapOf()

    constructor(eventHandlers: List<EventHandler<out DomainEvent>>) {

        registeredCommandHandlers = eventHandlers.groupBy { it.isAssignedTo() }

    }

    override fun <E : DomainEvent> dispatch(event: E) {

        registeredCommandHandlers[event::class]?.forEach {
            @Suppress("UNCHECKED_CAST")
            val eventHandler = it as EventHandler<E>
            eventHandler.handle(event)
        }

    }

}
