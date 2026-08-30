package br.com.ajudafio.book.domain.usecase

import br.com.ajudafio.book.domain.model.Book
import br.com.ajudafio.book.domain.repository.BookRepository
import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result

class GetBookDetailsUseCase(
    private val repository: BookRepository,
) {
    suspend operator fun invoke(id: String): Result<Book, DataError.Remote> =
        repository.getBookById(id)
}
