package br.com.ajudafio.presentation.book.list

import br.com.ajudafio.feature.book.domain.model.Book
import br.com.ajudafio.presentation.UiText

data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
val books = (1..100).map { index ->
    Book(
        id = "OL${index}M",
        title = "Book Title $index",
        authors = listOf("Author ${index % 10 + 1}"),
        description = "Description for book $index. A short summary of what this book is about.",
        coverUrl = "https://covers.openlibrary.org/b/id/${1000 + index}-L.jpg",
        languages = listOf("eng"),
        firstPublishYear = (1950 + index % 70).toString(),
        averageRating = (index % 5) + 1.0,
        ratingCount = index * 13,
        numPages = 100 + index * 3,
        numEditions = index % 8 + 1
    )
}