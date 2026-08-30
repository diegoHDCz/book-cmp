package br.com.ajudafio.book.di

import br.com.ajudafio.book.domain.model.Book
import br.com.ajudafio.book.domain.repository.BookRepository
import br.com.ajudafio.core.domain.DataError
import br.com.ajudafio.core.domain.Result
import kotlin.coroutines.cancellation.CancellationException
import org.koin.mp.KoinPlatform

class BookLoadException(override val message: String) : Exception(message)

private fun DataError.Remote.toMessage(): String = when (this) {
    DataError.Remote.REQUEST_TIMEOUT -> "A requisição demorou demais. Tente novamente."
    DataError.Remote.TOO_MANY_REQUESTS -> "Muitas requisições. Aguarde um momento."
    DataError.Remote.NO_INTERNET -> "Sem conexão com a internet."
    DataError.Remote.SERVER -> "Erro no servidor. Tente mais tarde."
    DataError.Remote.SERIALIZATION -> "Não foi possível processar a resposta."
    DataError.Remote.UNKNOWN -> "Algo deu errado. Tente novamente."
}

private fun <D> Result<D, DataError.Remote>.getOrThrow(): D = when (this) {
    is Result.Success -> data
    is Result.Error -> throw BookLoadException(error.toMessage())
}

// Fachada chamável a partir do Swift: traduz o Result<D, DataError> do domínio
// (que não bridga bem pro Swift) em suspend fun throwing, que o Kotlin/Native
// exporta como async throws nativo do lado Swift.
object BookGateway {
    private val bookRepository: BookRepository
        get() = KoinPlatform.getKoin().get()

    @Throws(BookLoadException::class, CancellationException::class)
    suspend fun searchBooks(query: String): List<Book> =
        bookRepository.searchBooks(query).getOrThrow()

    @Throws(BookLoadException::class, CancellationException::class)
    suspend fun getBookById(id: String): Book =
        bookRepository.getBookById(id).getOrThrow()
}
