package com.plop.bankingkotlin.account.write.infrastructure.config

import com.plop.bankingkotlin.account.write.command.OpenAccountHandler
import com.plop.bankingkotlin.account.write.infrastructure.persistence.InMemoryAccountEventStore
import com.plop.bankingkotlin.buildingBlocks.CommandDispatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Injection {

    @Bean
    fun eventBus() = CommandDispatcher(listOf(OpenAccountHandler()))

    @Bean
    fun accountEventStore() = InMemoryAccountEventStore()

}
