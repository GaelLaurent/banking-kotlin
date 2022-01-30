package com.plop.bankingkotlin.account.command.domain

import com.plop.bankingkotlin.buildingBlocks.AggregateRoot

class Account(private val accountId: AccountId) : AggregateRoot {

    private var changes = mutableListOf<AccountOpened>()

    override fun getId(): String {
        return accountId.getValue().toString()
    }

    private fun applyChanges(event: AccountOpened) {
        changes.add(event)
    }

    fun getUncommittedChanges(): List<AccountOpened> {
        return changes.toList()
    }

    companion object {
        fun open(
            accountId: AccountId,
            firstname: Firstname,
            lastname: Lastname,
            email: Email,
            phoneNumber: PhoneNumber,
            currency: Currency,
            firstDeposit: MoneyAmount
        ): Account {

            val account = Account(accountId)

            val event = AccountOpened(accountId.getValue(), firstname.getValue(), lastname.getValue(), email.getValue(), phoneNumber.getValue(), currency, firstDeposit.getValue())

            account.applyChanges(event)

            return account
        }
    }

}
