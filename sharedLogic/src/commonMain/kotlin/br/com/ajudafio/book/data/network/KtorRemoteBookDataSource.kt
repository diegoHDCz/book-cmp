package br.com.ajudafio.book.data.network

import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result
import br.com.ajudafio.book.data.dto.SearchResponseDto
import br.com.ajudafio.book.data.dto.SearchedBookDto
import br.com.ajudafio.book.data.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) : RemoteBookDataSource {

    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote>{
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/search.json"
            ){
                parameter("q",query)
                parameter("limit",resultLimit)
                parameter("language","eng")
                parameter("fields", "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count")
            }
        }
    }

    override suspend fun getBookById(id: String): Result<SearchedBookDto, DataError.Remote> {
        // TODO: trocar pelo endpoint de detalhes (ex: /works/$id.json) quando o mapeamento do payload for definido.
        return Result.Error(DataError.Remote.UNKNOWN)
    }
}