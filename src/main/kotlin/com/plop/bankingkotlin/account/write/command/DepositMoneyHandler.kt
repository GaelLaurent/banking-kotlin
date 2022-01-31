package com.plop.bankingkotlin.account.write.command

import com.plop.bankingkotlin.account.write.domain.AccountEventStore
import com.plop.bankingkotlin.account.write.domain.MoneyAmount
import com.plop.bankingkotlin.buildingBlocks.CommandHandler

class DepositMoneyHandler(private val accountEventStore: AccountEventStore)
    : CommandHandler<DepositMoney> {

    override fun isAssignedTo() = DepositMoney::class

    override fun handle(command: DepositMoney) {

        val account = accountEventStore.getById(command.accountId)

        account.depositMoney(MoneyAmount.create(command.value, command.currency))

        val change = account.getUncommittedChange()

        accountEventStore.store(change)

    }

}
