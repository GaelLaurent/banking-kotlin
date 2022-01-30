package com.plop.bankingkotlin.account.write.domain

import java.util.*

class AccountId private constructor(private val value: UUID) {

    fun getValue(): UUID {
        return value
    }

    companion object {
        fun create(value: UUID) = AccountId(value)
    }

}
