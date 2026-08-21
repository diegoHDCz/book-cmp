package br.com.ajudafio.presentation.book.detail

sealed interface BookDetailAction {
    data object OnRetry : BookDetailAction
    data object OnBackClick : BookDetailAction
}
