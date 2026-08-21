package br.com.ajudafio.feature.book.domain.usecase

import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result
import br.com.ajudafio.feature.book.domain.model.Book
import br.com.ajudafio.feature.book.domain.repository.BookRepository

class GetBookDetailsUseCase(
    private val repository: BookRepository,
) {
    suspend operator fun invoke(id: String): Result<Book, DataError.Remote> =
        repository.getBookById(id)
}
