package br.com.ajudafio.presentation.book.list

import br.com.ajudafio.book.domain.model.Book
import br.com.ajudafio.presentation.UiText

data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
