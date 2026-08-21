package br.com.ajudafio.feature.book.domain.repository

import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result
import br.com.ajudafio.feature.book.domain.model.Book

/**
 * Contrato do domain. A implementação (data) conhece esta interface,
 * nunca o contrário — dependência sempre aponta pra dentro.
 */
interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookById(id: String): Result<Book, DataError.Remote>
}
