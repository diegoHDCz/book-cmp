package br.com.ajudafio.domain.usecase

import br.com.ajudafio.domain.model.Greeting
import br.com.ajudafio.domain.repository.GreetingRepository

class GetGreetingUseCase(
    private val repository: GreetingRepository,
) {
    operator fun invoke(): Greeting = repository.getGreeting()
}
