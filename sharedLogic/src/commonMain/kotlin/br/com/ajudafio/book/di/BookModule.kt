package br.com.ajudafio.book.di

import br.com.ajudafio.book.data.HttpClientFactory
import br.com.ajudafio.book.data.network.KtorRemoteBookDataSource
import br.com.ajudafio.book.data.network.RemoteBookDataSource
import br.com.ajudafio.book.data.repository.DefaultBookRepository
import br.com.ajudafio.book.domain.repository.BookRepository
import br.com.ajudafio.book.domain.usecase.GetBookDetailsUseCase
import br.com.ajudafio.book.domain.usecase.SearchBooksUseCase
import org.koin.dsl.module

/**
 * DI da feature. Cada feature expõe o seu módulo; o core só agrega.
 */
val bookModule = module {
    single { HttpClientFactory.create() }
    single<RemoteBookDataSource> { KtorRemoteBookDataSource(get()) }
    single<BookRepository> { DefaultBookRepository(get()) }
    factory { SearchBooksUseCase(get()) }
    factory { GetBookDetailsUseCase(get()) }
}
