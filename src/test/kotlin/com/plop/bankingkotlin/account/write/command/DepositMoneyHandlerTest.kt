package com.plop.bankingkotlin.account.write.command

import com.plop.bankingkotlin.account.write.domain.AccountOpened
import com.plop.bankingkotlin.account.write.domain.Currency
import com.plop.bankingkotlin.account.write.domain.MoneyDeposited
import com.plop.bankingkotlin.account.write.infrastructure.persistence.InMemoryAccountEventStore
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DepositMoneyHandlerTest {

    @Test
    fun `a user should be able to deposit money to its account`() {

        // given
        val eventStore = InMemoryAccountEventStore()
        eventStore.store(AccountOpened("c38fb97f-b7d7-4a92-858e-a49f6550adf1","Bob", "Mc Donald", "bob.macdonald@plop.com", "05 03 03 03 03", Currency.DOLLAR, 42f))
        val eventStoreSpy = AccountEventStoreSpy(eventStore)

        val depositMoney = DepositMoney("c38fb97f-b7d7-4a92-858e-a49f6550adf1", 50f, Currency.DOLLAR)

        val handler = DepositMoneyHandler(eventStoreSpy)

        val expectedEvent = MoneyDeposited("c38fb97f-b7d7-4a92-858e-a49f6550adf1", 50f, Currency.DOLLAR)

        // when
        handler.handle(depositMoney)

        // then
        Assertions.assertThat(eventStoreSpy.getEventsStored()).isEqualTo(mutableSetOf(expectedEvent))

    }

}
