package com.plop.bankingkotlin.account.write.domain

import java.util.*

data class AccountId private constructor(val value: UUID) {

    companion object {
        fun create(value: UUID) = AccountId(value)
    }

}
