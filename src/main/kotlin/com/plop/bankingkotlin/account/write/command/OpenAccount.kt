package com.plop.bankingkotlin.account.write.command

import com.plop.bankingkotlin.account.write.domain.Currency
import com.plop.bankingkotlin.buildingBlocks.Command

data class OpenAccount(
    val accountId: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phoneNumber: String,
    val currency: Currency,
    val firstDeposit: Float
) : Command {
}
