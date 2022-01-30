package com.plop.bankingkotlin.account.write.infrastructure.controller

import com.plop.bankingkotlin.account.write.command.OpenAccount
import com.plop.bankingkotlin.account.write.domain.AccountEventStore
import com.plop.bankingkotlin.buildingBlocks.CommandBusMiddleware
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@CrossOrigin(origins = ["*"], exposedHeaders = ["Location"])
@RequestMapping("/account")
class AccountController(private val commandBus: CommandBusMiddleware, private val accountEventStore: AccountEventStore) {

    @PostMapping
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

}
