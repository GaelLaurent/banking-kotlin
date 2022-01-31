package com.plop.bankingkotlin.account.write.infrastructure.persistence

import com.plop.bankingkotlin.account.write.domain.AccountHistory
import com.plop.bankingkotlin.account.write.domain.AccountOpened
import com.plop.bankingkotlin.account.write.domain.Currency
import com.plop.bankingkotlin.account.write.domain.MoneyDeposited
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InMemoryAccountEventStoreITest {

    @Test
    fun `should get an aggregate from its event`() {
        // given
        val id = "8394050c-d533-45a8-be6e-d13c53908536"

        val eventStore = InMemoryAccountEventStore()

        // when
        eventStore.store(AccountOpened(id,"Bob", "Mc Donald", "bob.macdonald@plop.com", "05 03 03 03 03", Currency.DOLLAR, 50f))
        eventStore.store(MoneyDeposited(id, 50f, Currency.DOLLAR))
        eventStore.store(AccountOpened("555be785-f92c-45ec-9dee-c3ae9632bf53","Bob", "Mc Donald", "bob.macdonald@plop.com", "05 03 03 03 03", Currency.DOLLAR, 50f))
        val aggregateHistory = eventStore.getById(id)

        val accountHistoryExpected = AccountHistory(
            id,
            listOf(
                AccountOpened(id, "Bob", "Mc Donald", "bob.macdonald@plop.com", "05 03 03 03 03", Currency.DOLLAR, 50f),
                MoneyDeposited(id, 50f, Currency.DOLLAR)
            )
        )

        // then
        assertThat(aggregateHistory).isEqualTo(accountHistoryExpected)

    }

    @Test
    fun `should throw an exception if the aggregate is not found`() {
        // given
        val id = "8394050c-d533-45a8-be6e-d13c53908536"

        val eventStore = InMemoryAccountEventStore()

        // when
        // then
        Assertions.assertThatExceptionOfType(PersistenceException::class.java).isThrownBy {
            eventStore.getById(id)
        }.withMessage("Aggregate not found: [${id}]")

    }

    @Test
    fun `should return different id after several id generation`() {

        // given
        val eventStore = InMemoryAccountEventStore()

        // when
        val id1 = eventStore.nextId()
        val id2 = eventStore.nextId()
        val id3 = eventStore.nextId()

        // then
        assertThat(setOf(id1, id2, id3)).hasSize(3)

    }

}
