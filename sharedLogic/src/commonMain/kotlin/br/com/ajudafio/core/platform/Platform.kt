package br.com.ajudafio.core.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
