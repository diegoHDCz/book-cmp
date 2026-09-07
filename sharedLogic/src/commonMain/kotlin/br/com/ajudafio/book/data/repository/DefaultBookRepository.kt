package br.com.ajudafio.book.data.repository

import br.com.ajudafio.book.data.mappers.toBook
import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result
import br.com.ajudafio.book.data.network.RemoteBookDataSource
import br.com.ajudafio.book.domain.model.Book
import br.com.ajudafio.book.domain.repository.BookRepository
import br.com.ajudafio.core.domain.map
import kotlin.collections.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {
    override suspend fun searchBooks(query: String) : Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query).map { dto ->
            dto.results.map { it.toBook() }
        }
    }

    override suspend fun getBookById(id: String): Result<Book, DataError.Remote> {
        return remoteBookDataSource.getBookDetails(id).map { it.toBook(id) }
    }
}