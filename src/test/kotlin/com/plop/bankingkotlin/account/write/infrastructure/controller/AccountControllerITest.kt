package com.plop.bankingkotlin.account.write.infrastructure.controller

import com.plop.bankingkotlin.account.write.command.OpenAccount
import com.plop.bankingkotlin.account.write.domain.AccountEventStore
import com.plop.bankingkotlin.account.write.domain.Currency
import com.plop.bankingkotlin.buildingBlocks.Command
import com.plop.bankingkotlin.buildingBlocks.CommandBusMiddleware
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:test.properties"])
internal class AccountControllerITest(
    @Autowired val testRestTemplate: TestRestTemplate,
    @Autowired val accountEventStoreFake: AccountEventStoreFake,
    @Autowired val commandBusFake: CommandBusFake
) {

    @TestConfiguration
    internal class MissionControllerWriteITestConfiguration {

        @Bean
        fun accountEventStore(): AccountEventStore = AccountEventStoreFake()

        @Bean
        fun commandBus(): CommandBusMiddleware = CommandBusFake()

    }

    @Test
    fun `should return 201 when create a account`() {
        // given
        val id = accountEventStoreFake.nextId()

        val openAccountRequest = """
            {
                "firstname": "Bob",
                "lastname": "Mc Donald",
                "email": "bob.macdonald@plop.com",
                "phoneNumber": "05 03 03 03 03",
                "currency": "DOLLAR",
                "firstDeposit": 42
            }
        """

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        // when
        val request = HttpEntity(openAccountRequest, headers)
        val response = testRestTemplate.postForEntity(
            "/account",
            request,
            String::class.java
        )

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(response.headers.location.toString()).isEqualTo("account/${id}")

        assertThat(commandBusFake.commandDispatched).isEqualTo(OpenAccount(id,
            "Bob", "Mc Donald", "bob.macdonald@plop.com",
            "05 03 03 03 03", Currency.DOLLAR, 42f))

    }

}

class AccountEventStoreFake : AccountEventStore {
    override fun nextId(): String {
        return "d5c4ffca-45e3-41fe-8ade-4fedbeafabb0"
    }

}

class CommandBusFake : CommandBusMiddleware {

    var commandDispatched: Command? = null

    override fun <C : Command> dispatch(command: C) {
        commandDispatched = command
    }

}
