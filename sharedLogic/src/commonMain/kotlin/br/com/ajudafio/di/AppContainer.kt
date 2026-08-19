package br.com.ajudafio.di

import br.com.ajudafio.data.repository.GreetingRepositoryImpl
import br.com.ajudafio.domain.repository.GreetingRepository
import br.com.ajudafio.domain.usecase.GetGreetingUseCase

/**
 * Composition root manual (sem framework de DI ainda).
 * Quando o projeto crescer, troque por módulos Koin mantendo os mesmos contratos de domain.
 */
object AppContainer {
    private val greetingRepository: GreetingRepository = GreetingRepositoryImpl()

    val getGreetingUseCase: GetGreetingUseCase = GetGreetingUseCase(greetingRepository)
}
