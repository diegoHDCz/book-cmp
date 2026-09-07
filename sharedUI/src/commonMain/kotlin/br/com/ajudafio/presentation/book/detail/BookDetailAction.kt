package br.com.ajudafio.presentation.book.detail

import br.com.ajudafio.book.domain.model.Book

sealed interface BookDetailAction {
    data object OnFavoriteClick : BookDetailAction
    data object OnBackClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}
