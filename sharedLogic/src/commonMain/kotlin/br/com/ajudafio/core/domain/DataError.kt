package br.com.ajudafio.core.domain

/**
 * Erros transversais de dados. Fica no core porque toda feature que
 * consome fonte remota/local vai reusar os mesmos casos.
 */
sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}