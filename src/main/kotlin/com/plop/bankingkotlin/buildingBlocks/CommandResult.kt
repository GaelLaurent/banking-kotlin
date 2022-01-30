package com.plop.bankingkotlin.buildingBlocks

data class CommandResult(val event: DomainEvent) {

    companion object {
        fun of(event: DomainEvent): CommandResult {
            return CommandResult(event)
        }
    }

}
