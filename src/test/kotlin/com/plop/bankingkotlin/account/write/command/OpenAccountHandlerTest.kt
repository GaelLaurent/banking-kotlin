package com.plop.bankingkotlin.account.write.command

import com.plop.bankingkotlin.account.write.domain.AccountOpened
import com.plop.bankingkotlin.account.write.domain.Currency
import com.plop.bankingkotlin.account.write.infrastructure.persistence.InMemoryAccountEventStore
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class OpenAccountHandlerTest {

    @Test
    fun `a user should be able to open a account`() {

        // given
        val eventStoreSpy = AccountEventStoreSpy(InMemoryAccountEventStore())

        val openAccount = OpenAccount("c38fb97f-b7d7-4a92-858e-a49f6550adf1",
            "Bob", "Mc Donald", "bob.macdonald@plop.com",
            "05 03 03 03 03", Currency.DOLLAR, 42f)

        val handler = OpenAccountHandler(eventStoreSpy)

        val expectedEvent = AccountOpened("c38fb97f-b7d7-4a92-858e-a49f6550adf1","Bob", "Mc Donald", "bob.macdonald@plop.com", "05 03 03 03 03", Currency.DOLLAR, 42f)

        // when
        handler.handle(openAccount)

        // then
        Assertions.assertThat(eventStoreSpy.getEventsStored()).isEqualTo(mutableSetOf(expectedEvent))

    }
}

