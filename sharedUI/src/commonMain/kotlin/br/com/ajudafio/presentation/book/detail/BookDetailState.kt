package br.com.ajudafio.presentation.book.detail

import br.com.ajudafio.book.domain.model.Book
import br.com.ajudafio.presentation.UiText

data class BookDetailState(
    val isLoading: Boolean = false,
    val book: Book? = null,
    val errorMessage: UiText? = null,
    val isFavorite: Boolean = false
)
