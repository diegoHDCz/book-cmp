package br.com.ajudafio.book.domain.repository

import br.com.ajudafio.book.domain.model.Book
import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result


interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookById(id: String): Result<Book, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>
}
