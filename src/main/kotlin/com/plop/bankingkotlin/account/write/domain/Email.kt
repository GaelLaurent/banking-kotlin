package com.plop.bankingkotlin.account.write.domain

class Email private constructor(private val value: String) {

    fun getValue(): String {
        return value
    }

    companion object {
        fun create(value: String) = Email(value)
    }

}
