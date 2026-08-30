package br.com.ajudafio.book.domain.usecase

import br.com.ajudafio.book.domain.model.Book
import br.com.ajudafio.book.domain.repository.BookRepository
import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result

class SearchBooksUseCase(
    private val repository: BookRepository,
) {
    suspend operator fun invoke(query: String): Result<List<Book>, DataError.Remote> =
        repository.searchBooks(query)
}
