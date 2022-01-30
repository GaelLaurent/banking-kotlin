package com.plop.bankingkotlin.account.write.domain

data class PhoneNumber private constructor(val value: String) {

    companion object {
        fun create(value: String) = PhoneNumber(value)
    }

}
