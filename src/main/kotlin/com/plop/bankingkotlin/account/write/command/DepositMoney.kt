package com.plop.bankingkotlin.account.write.command

import com.plop.bankingkotlin.account.write.domain.Currency
import com.plop.bankingkotlin.buildingBlocks.Command

data class DepositMoney(
    val accountId: String,
    val value: Float,
    val currency: Currency
) : Command {
}
