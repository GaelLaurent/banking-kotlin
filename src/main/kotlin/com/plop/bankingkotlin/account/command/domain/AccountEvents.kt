package com.plop.bankingkotlin.account.command.domain

import com.plop.bankingkotlin.buildingBlocks.DomainEvent
import java.util.*

data class AccountOpened(
    val accountUUID: UUID,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phoneNumber: String,
    val currency: Currency,
    val firstDeposit: Float
): DomainEvent
