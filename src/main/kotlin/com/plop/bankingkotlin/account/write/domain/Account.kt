package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.AggregateRoot
import com.plop.bankingkotlin.buildingBlocks.DomainEvent
import com.plop.bankingkotlin.buildingBlocks.NothingHappened

class Account(private val accountId: AccountId) : AggregateRoot {

    private var change: DomainEvent = NothingHappened()

    override fun getId(): String {
        return accountId.getValue().toString()
    }

    private fun applyChanges(event: DomainEvent) {
        change = event
    }

    fun getUncommittedChanges(): DomainEvent {
        return change
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

            val event = AccountOpened(accountId.getValue().toString(), firstname.getValue(), lastname.getValue(), email.getValue(), phoneNumber.getValue(), currency, firstDeposit.getValue())

            account.applyChanges(event)

            return account
        }
    }

}
