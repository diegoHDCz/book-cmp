package br.com.ajudafio.presentation.book.list

import br.com.ajudafio.feature.book.domain.model.Book

sealed interface BookListAction {
    data class OnSearchQueryChange(val query: String): BookListAction
    data class OnBookClick(val book: Book): BookListAction
    data class onTabSelected(val index: Int): BookListAction
}
