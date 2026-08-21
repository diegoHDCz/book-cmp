package br.com.ajudafio.feature.book.di

import br.com.ajudafio.feature.book.data.remote.BookRemoteDataSource
import br.com.ajudafio.feature.book.data.remote.KtorBookRemoteDataSource
import br.com.ajudafio.feature.book.data.repository.BookRepositoryImpl
import br.com.ajudafio.feature.book.domain.repository.BookRepository
import br.com.ajudafio.feature.book.domain.usecase.GetBookDetailsUseCase
import br.com.ajudafio.feature.book.domain.usecase.SearchBooksUseCase
import org.koin.dsl.module

/**
 * DI da feature. Cada feature expõe o seu módulo; o core só agrega.
 */
val bookModule = module {
    single<BookRemoteDataSource> { KtorBookRemoteDataSource() }
    single<BookRepository> { BookRepositoryImpl(get()) }
    factory { SearchBooksUseCase(get()) }
    factory { GetBookDetailsUseCase(get()) }
}
