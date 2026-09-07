package br.com.ajudafio.book.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import br.com.ajudafio.book.data.local.BookDao
import br.com.ajudafio.book.data.local.BookDatabase
import br.com.ajudafio.book.data.local.BookLocalDataSource
import br.com.ajudafio.book.data.local.DatabaseBuilderFactory
import br.com.ajudafio.book.data.local.RoomBookLocalDataSource
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }

        single { DatabaseBuilderFactory() }
        single {
            get<DatabaseBuilderFactory>().create()
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.Default)
                .build()
        }
        single<BookDao> { get<BookDatabase>().bookDao() }
        singleOf(::RoomBookLocalDataSource).bind<BookLocalDataSource>()
    }