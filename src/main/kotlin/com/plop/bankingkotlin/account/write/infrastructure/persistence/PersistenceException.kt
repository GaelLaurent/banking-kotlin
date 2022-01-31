package com.plop.bankingkotlin.account.write.infrastructure.persistence

class PersistenceException private constructor (override val message: String?): Exception(message) {

    companion object {
        fun aggregateNotFound(id: String) = PersistenceException("Aggregate not found: [${id}]")
    }

}

