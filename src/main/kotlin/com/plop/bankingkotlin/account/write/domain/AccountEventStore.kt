package com.plop.bankingkotlin.account.write.domain

import com.plop.bankingkotlin.buildingBlocks.EventStore

interface AccountEventStore: EventStore<AccountEvent>
