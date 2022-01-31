package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.AggregateHistory
import com.plop.bankingkotlin.buildingBlocks.AggregateRoot
import java.util.*

class Account private constructor(private val accountId: AccountId) : AggregateRoot {

    private var change: AccountEvent = NothingHappened

    override fun getId(): String {
        return accountId.value.toString()
    }

    private fun applyChanges(event: AccountEvent) {
        change = event
    }

    fun getUncommittedChange(): AccountEvent {
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

        fun fromHistory(history: AggregateHistory<AccountEvent>): Account {
            return Account(AccountId.create(UUID.fromString(history.aggregateId)))
        }
    }

}
