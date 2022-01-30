package com.plop.bankingkotlin.account.write.domain

data class Email private constructor(val value: String) {

    companion object {
        fun create(value: String) = Email(value)
    }

}
