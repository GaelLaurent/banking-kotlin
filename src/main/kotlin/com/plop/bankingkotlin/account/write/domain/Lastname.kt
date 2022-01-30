package com.plop.bankingkotlin.account.write.domain

data class Lastname private constructor(val value: String) {

    companion object {
        fun create(value: String) = Lastname(value)
    }

}
