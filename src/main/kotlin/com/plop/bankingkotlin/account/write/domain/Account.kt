package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.AggregateRoot
import com.plop.bankingkotlin.buildingBlocks.DomainEvent

class Account(private val accountId: AccountId) : AggregateRoot {

    private var changes = mutableListOf<DomainEvent>()

    override fun getId(): String {
        return accountId.getValue().toString()
    }

    private fun applyChanges(event: DomainEvent) {
        changes.add(event)
    }

    fun getUncommittedChanges(): List<DomainEvent> {
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

            if (currency == firstDeposit.getCurrency()) {

                val event = AccountOpened(accountId.getValue().toString(), firstname.getValue(), lastname.getValue(), email.getValue(), phoneNumber.getValue(), currency, firstDeposit.getValue())
                account.applyChanges(event)

            } else {

                val event = AccountOpeningRejected(accountId.getValue().toString(), firstname.getValue(), lastname.getValue(), email.getValue(), phoneNumber.getValue())
                account.applyChanges(event)

            }


            return account
        }
    }

}
