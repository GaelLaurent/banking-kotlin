package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.DomainEvent

data class AccountHistory(val accountId: AccountId, val events: List<DomainEvent>)
