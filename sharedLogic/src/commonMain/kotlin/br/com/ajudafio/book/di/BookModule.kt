package br.com.ajudafio.book.di

import br.com.ajudafio.book.data.HttpClientFactory
import br.com.ajudafio.book.data.network.KtorRemoteBookDataSource
import br.com.ajudafio.book.data.network.RemoteBookDataSource
import br.com.ajudafio.book.data.repository.DefaultBookRepository
import br.com.ajudafio.book.domain.repository.BookRepository
import org.koin.core.module.Module

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val bookModule = module {
    single { HttpClientFactory.create() }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()
}
