package com.plop.bankingkotlin.account.write.infrastructure.controller

import com.plop.bankingkotlin.account.write.domain.Currency

data class DepositMoneyRequest(
    val value: Float,
    val currency: Currency
)
