package br.com.ajudafio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import br.com.ajudafio.core.theme.AppTheme
import br.com.ajudafio.presentation.book.list.BookListScreen
import br.com.ajudafio.presentation.book.list.BookListViewModel

@Composable
@Preview
fun App() {
    AppTheme {
        BookListScreen(viewModel = remember {
            BookListViewModel(

            )
        }, onBookClick = {})
    }
}
