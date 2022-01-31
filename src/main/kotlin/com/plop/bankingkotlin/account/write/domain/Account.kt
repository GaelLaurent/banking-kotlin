package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.AggregateRoot
import com.plop.bankingkotlin.buildingBlocks.DomainEvent
import java.util.*

class Account private constructor(private val accountId: AccountId) : AggregateRoot {

    private var change: DomainEvent = NothingHappened

    override fun getId(): String {
        return accountId.value.toString()
    }

    private fun applyChanges(event: DomainEvent) {
        change = event
    }

    fun getUncommittedChange(): DomainEvent {
        return change
    }

    fun depositMoney(moneyAmount: MoneyAmount) {
        applyChanges(MoneyDeposited(accountId.value.toString(), moneyAmount.value, moneyAmount.currency))
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

            val event = AccountOpened(accountId.value.toString(), firstname.value, lastname.value, email.value, phoneNumber.value, currency, firstDeposit.value)

            account.applyChanges(event)

            return account
        }

        fun fromHistory(history: AccountHistory): Account {
            return Account(AccountId.create(UUID.fromString(history.accountId)))
        }
    }

}
