package com.plop.bankingkotlin.account.write.domain

data class MoneyAmount private constructor(val value: Float, val currency: Currency) {

    companion object {
        fun create(value: Float, currency: Currency) = MoneyAmount(value, currency)
    }

}
