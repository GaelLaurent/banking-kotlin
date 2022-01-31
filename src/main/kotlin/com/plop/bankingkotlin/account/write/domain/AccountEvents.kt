package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.DomainEvent

sealed class AccountEvent : DomainEvent {
    override fun getAggregateId(): String = accountId

    abstract val accountId: String
}

data class AccountOpened(
    override val accountId: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phoneNumber: String,
    val currency: Currency,
    val firstDeposit: Float
): AccountEvent()

data class MoneyDeposited(
    override val accountId: String,
    val value: Float,
    val currency: Currency
): AccountEvent()

class NothingHappened(override val accountId: String) : AccountEvent()
