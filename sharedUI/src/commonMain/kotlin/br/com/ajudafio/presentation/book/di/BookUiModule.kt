package br.com.ajudafio.presentation.book.di

import br.com.ajudafio.presentation.book.detail.BookDetailViewModel
import br.com.ajudafio.presentation.book.list.BookListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Módulos de presentation da feature book. Vive no sharedUI e é passado
 * ao initKoin(extraModules = ...) pelo app de cada plataforma.
 */
val bookUiModule = module {
    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)

}
