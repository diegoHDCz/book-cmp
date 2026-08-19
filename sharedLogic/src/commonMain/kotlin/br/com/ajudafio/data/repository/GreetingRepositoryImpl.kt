package br.com.ajudafio.data.repository

import br.com.ajudafio.core.platform.getPlatform
import br.com.ajudafio.domain.model.Greeting
import br.com.ajudafio.domain.repository.GreetingRepository

class GreetingRepositoryImpl : GreetingRepository {
    override fun getGreeting(): Greeting {
        val platformName = getPlatform().name
        return Greeting(message = "Hello, $platformName!")
    }
}
