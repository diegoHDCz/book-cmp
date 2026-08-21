package br.com.ajudafio.feature.book.data.remote.dto

/**
 * Espelha o payload da API. Fica só na camada data.
 * TODO: anotar com @Serializable quando o plugin kotlinx-serialization
 * for aplicado (junto com o setup do Ktor).
 */
data class BookDto(
    val id: String,
    val title: String,
    val authors: List<String> = emptyList(),
    val description: String? = null,
    val coverUrl: String? = null,
)
