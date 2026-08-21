package br.com.ajudafio.feature.book.data.remote

import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result
import br.com.ajudafio.feature.book.data.remote.dto.BookDto

/**
 * TODO: injetar HttpClient (Ktor) via construtor quando o networking
 * estiver configurado no core, e substituir os stubs abaixo pelas
 * chamadas HTTP reais (mapeando exceções -> DataError.Remote).
 */
class KtorBookRemoteDataSource(
    // private val httpClient: HttpClient,
) : BookRemoteDataSource {

    override suspend fun searchBooks(query: String): Result<List<BookDto>, DataError.Remote> {
        // TODO: httpClient.get(".../search?q=$query") -> body() -> Result.Success(...)
        return Result.Error(DataError.Remote.UNKNOWN)
    }

    override suspend fun getBookById(id: String): Result<BookDto, DataError.Remote> {
        // TODO: httpClient.get(".../books/$id") -> body() -> Result.Success(...)
        return Result.Error(DataError.Remote.UNKNOWN)
    }
}
