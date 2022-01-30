package com.plop.bankingkotlin.account.write.infrastructure.persistence

import com.plop.bankingkotlin.account.write.domain.AccountEventStore
import org.springframework.stereotype.Component
import java.util.*

@Component
class InMemoryAccountEventStore: AccountEventStore {

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

}
