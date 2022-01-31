package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.DomainEvent

data class AccountOpened(
    override val aggregateId: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phoneNumber: String,
    val currency: Currency,
    val firstDeposit: Float
): DomainEvent

data class MoneyDeposited(
    override val aggregateId: String,
    val value: Float,
    val currency: Currency
): DomainEvent

class NothingHappened(override val aggregateId: String) : DomainEvent
