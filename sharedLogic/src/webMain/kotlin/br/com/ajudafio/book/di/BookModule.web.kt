package br.com.ajudafio.book.di

import br.com.ajudafio.book.data.local.BookLocalDataSource
import br.com.ajudafio.book.data.local.InMemoryBookLocalDataSource
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: org.koin.core.module.Module
    get() = module {
        single<HttpClientEngine> { Js.create() }
        single { InMemoryBookLocalDataSource() }.bind<BookLocalDataSource>()
    }