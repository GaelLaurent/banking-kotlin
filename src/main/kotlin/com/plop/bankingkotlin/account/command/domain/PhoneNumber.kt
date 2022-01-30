package com.plop.bankingkotlin.account.command.domain

class PhoneNumber private constructor(private val value: String) {

    fun getValue(): String {
        return value
    }

    companion object {
        fun create(value: String) = PhoneNumber(value)
    }

}
