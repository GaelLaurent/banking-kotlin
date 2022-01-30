package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.DomainEvent

data class AccountOpened(
    val accountUUID: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phoneNumber: String,
    val currency: Currency,
    val firstDeposit: Float
): DomainEvent

data class AccountOpeningRejected(
    val accountUUID: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phoneNumber: String,
): DomainEvent
