package com.plop.bankingkotlin.account.command.domain

class MoneyAmount private constructor(private val value: Float, private val currency: Currency) {

    fun getValue(): Float {
        return value
    }

    fun getCurrency(): Currency {
        return currency
    }

    companion object {
        fun create(value: Float, currency: Currency) = MoneyAmount(value, currency)
    }

}
