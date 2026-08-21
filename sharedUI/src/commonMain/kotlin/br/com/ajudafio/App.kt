package br.com.ajudafio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import br.com.ajudafio.core.theme.AppTheme
import br.com.ajudafio.presentation.book.list.BookListScreen
import br.com.ajudafio.presentation.book.list.BookListState
import br.com.ajudafio.presentation.book.list.books

@Composable
@Preview
fun App() {
    AppTheme {
        val state = remember {
            BookListState(
                searchResults = books,
                favoriteBooks = books,
            )
        }
        BookListScreen(
            state = state,
            onAction = {},
        )
    }
}
