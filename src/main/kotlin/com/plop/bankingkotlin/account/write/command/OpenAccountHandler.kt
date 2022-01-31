package com.plop.bankingkotlin.account.write.command

import com.plop.bankingkotlin.account.write.domain.*
import com.plop.bankingkotlin.buildingBlocks.CommandHandler
import com.plop.bankingkotlin.buildingBlocks.CommandResult
import java.util.*

class OpenAccountHandler(private val accountEventStore: AccountEventStore): CommandHandler<OpenAccount> {

    override fun isAssignedTo() = OpenAccount::class

    override fun handle(command: OpenAccount): CommandResult {

        val accountUUID = UUID.fromString(command.accountId)
        val accountId = AccountId.create(accountUUID)

        val firstname = Firstname.create(command.firstname)
        val lastname = Lastname.create(command.lastname)
        val email = Email.create(command.email)
        val phoneNumber = PhoneNumber.create(command.phoneNumber)

        val firstDeposit = MoneyAmount.create(command.firstDeposit, command.currency)

        val account = Account.open(accountId, firstname, lastname, email, phoneNumber, command.currency, firstDeposit)

        val change = account.getUncommittedChange()

        accountEventStore.store(change)

        return CommandResult.of(change)
    }

}
