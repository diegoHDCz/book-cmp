package br.com.ajudafio.feature.book.data.remote

import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result
import br.com.ajudafio.feature.book.data.remote.dto.BookDto

/**
 * Abstrai a fonte remota. Trocar Ktor por outra lib (ou por um fake em teste)
 * não toca no repositório nem no domain.
 */
interface BookRemoteDataSource {
    suspend fun searchBooks(query: String): Result<List<BookDto>, DataError.Remote>
    suspend fun getBookById(id: String): Result<BookDto, DataError.Remote>
}
