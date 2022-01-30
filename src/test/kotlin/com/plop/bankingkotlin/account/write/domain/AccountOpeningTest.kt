package com.plop.bankingkotlin.account.write.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

internal class AccountOpeningTest {

    @Test
    fun `a user should be able to open an account given a first deposit`() {

        // given
        val accountUUID = UUID.fromString("82df9bd1-d2e1-4e80-9850-4247eb67bdd1")
        val accountId = AccountId.create(accountUUID)
        val firstname = Firstname.create("Bob")
        val lastname = Lastname.create("Mc Donald")
        val email = Email.create("bob.macdonald@plop.com")
        val phoneNumber = PhoneNumber.create("05 03 03 03 03")

        val currency = Currency.DOLLAR
        val firstDeposit = MoneyAmount.create(15.42f, currency)

        // when
        val account = Account.open(accountId, firstname, lastname, email, phoneNumber, currency, firstDeposit)
        val result = account.getUncommittedChanges()

        val expected = AccountOpened("82df9bd1-d2e1-4e80-9850-4247eb67bdd1","Bob", "Mc Donald", "bob.macdonald@plop.com", "05 03 03 03 03", Currency.DOLLAR, 15.42f)

        // then
        assertThat(result).isEqualTo(expected)

    }

    @Test
    fun `a user should not be able to open an account given a first deposit's currency different than the account one`() {

        // given
        val accountUUID = UUID.fromString("82df9bd1-d2e1-4e80-9850-4247eb67bdd1")
        val accountId = AccountId.create(accountUUID)
        val firstname = Firstname.create("Bob")
        val lastname = Lastname.create("Mc Donald")
        val email = Email.create("bob.macdonald@plop.com")
        val phoneNumber = PhoneNumber.create("05 03 03 03 03")

        val firstDeposit = MoneyAmount.create(15.42f, Currency.DOLLAR)

        // when
        val account = Account.open(accountId, firstname, lastname, email, phoneNumber, Currency.EURO, firstDeposit)
        val result = account.getUncommittedChanges()

        val expected = AccountOpeningRejected("82df9bd1-d2e1-4e80-9850-4247eb67bdd1","Bob", "Mc Donald", "bob.macdonald@plop.com", "05 03 03 03 03")

        // then
        assertThat(result).isEqualTo(expected)

    }

}
