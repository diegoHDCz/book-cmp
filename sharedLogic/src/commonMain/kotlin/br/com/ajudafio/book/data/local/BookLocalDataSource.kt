package br.com.ajudafio.book.data.local

import br.com.ajudafio.book.domain.model.Book

/**
 * Contrato de cache local do domínio Book. Fica em commonMain porque
 * é código puro Kotlin — quem implementa com Room (mobileMain) ou de
 * outra forma (webMain) é decisão de cada plataforma.
 */
interface BookLocalDataSource {
    suspend fun getBookById(id: String): Book?
    suspend fun upsertBook(book: Book)
}
