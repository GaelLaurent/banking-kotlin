package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.DomainEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DepositMoneyTest {

    @Test
    fun `a user should be able to deposit money in its existing account`() {

        // given
        val id = "82df9bd1-d2e1-4e80-9850-4247eb67bdd1"
        val events = listOf<DomainEvent>(
            AccountOpened(id,"Bob", "Mc Donald", "bob.macdonald@plop.com", "05 03 03 03 03", Currency.DOLLAR, 50f)
        )
        val account = Account.fromHistory(AccountHistory(id, events))

        val moneyAmount = MoneyAmount.create(56f, Currency.DOLLAR)

        val expected = MoneyDeposited(id, 56f, Currency.DOLLAR)

        // when
        account.depositMoney(moneyAmount)

        // then
        val result = account.getUncommittedChange()
        assertThat(result).isEqualTo(expected)

    }

}
