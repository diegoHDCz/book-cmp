package br.com.ajudafio.domain.repository

import br.com.ajudafio.domain.model.Greeting

interface GreetingRepository {
    fun getGreeting(): Greeting
}
