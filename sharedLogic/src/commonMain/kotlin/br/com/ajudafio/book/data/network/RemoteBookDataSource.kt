package br.com.ajudafio.book.data.network

import br.com.ajudafio.book.data.dto.BookWorkDto
import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result
import br.com.ajudafio.book.data.dto.SearchResponseDto
import br.com.ajudafio.book.data.dto.SearchedBookDto

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null,
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(id: String): Result<BookWorkDto, DataError.Remote>
}