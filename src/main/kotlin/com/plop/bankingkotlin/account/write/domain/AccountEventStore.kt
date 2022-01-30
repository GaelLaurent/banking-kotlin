package com.plop.bankingkotlin.account.write.domain

interface AccountEventStore {
    fun nextId(): String
}
