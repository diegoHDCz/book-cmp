package br.com.ajudafio.feature.book.data.repository

import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result
import br.com.ajudafio.core.domain.map
import br.com.ajudafio.feature.book.data.mapper.toBook
import br.com.ajudafio.feature.book.data.remote.BookRemoteDataSource
import br.com.ajudafio.feature.book.domain.model.Book
import br.com.ajudafio.feature.book.domain.repository.BookRepository

class BookRepositoryImpl(
    private val remote: BookRemoteDataSource,
) : BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> =
        remote.searchBooks(query).map { dtos -> dtos.map { it.toBook() } }

    override suspend fun getBookById(id: String): Result<Book, DataError.Remote> =
        remote.getBookById(id).map { it.toBook() }
}
