package com.plop.bankingkotlin.buildingBlocks

data class CommandResult(val events: List<DomainEvent>) {

    companion object {
        fun of(events: List<DomainEvent>): CommandResult {
            return CommandResult(events)
        }

        fun empty(): CommandResult {
            return CommandResult(listOf())
        }
    }

}
