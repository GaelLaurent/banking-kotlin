package com.plop.bankingkotlin.account.write.infrastructure.controller

import com.plop.bankingkotlin.account.write.command.DepositMoney
import com.plop.bankingkotlin.account.write.command.OpenAccount
import com.plop.bankingkotlin.account.write.domain.AccountEventStore
import com.plop.bankingkotlin.buildingBlocks.CommandBusMiddleware
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], exposedHeaders = ["Location"])
class AccountController(private val commandBus: CommandBusMiddleware, private val accountEventStore: AccountEventStore) {

    @PostMapping
    @RequestMapping("/account")
    fun creationQuestionnaire(
        @RequestBody accountRequest: OpenAccountRequest
    ): ResponseEntity<String> {

        val id = accountEventStore.nextId()

        val openAccount = OpenAccount(id,
            accountRequest.firstname, accountRequest.lastname, accountRequest.email,
            accountRequest.phoneNumber, accountRequest.currency, accountRequest.firstDeposit)

        commandBus.dispatch(openAccount)

        return ResponseEntity.created(URI("account/${id}")).build()
    }

    @PostMapping
    @RequestMapping("/account/{accountId}/deposit")
    fun creationQuestionnaire(
        @PathVariable accountId: String,
        @RequestBody deposit: DepositMoneyRequest
    ): ResponseEntity<Void> {

        val depositMoney = DepositMoney(accountId, deposit.value, deposit.currency)

        commandBus.dispatch(depositMoney)

        return ResponseEntity.noContent().build()
    }

}
