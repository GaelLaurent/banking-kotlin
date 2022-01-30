package com.plop.bankingkotlin.account.command.domain

class Lastname private constructor(private val value: String) {

    fun getValue(): String {
        return value
    }

    companion object {
        fun create(value: String) = Lastname(value)
    }

}
