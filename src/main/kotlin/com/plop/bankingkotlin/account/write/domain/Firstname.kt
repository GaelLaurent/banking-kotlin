package com.plop.bankingkotlin.account.write.domain

data class Firstname private constructor(val value: String) {

    companion object {
        fun create(value: String) = Firstname(value)
    }

}
