package br.com.ajudafio

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.ajudafio.core.theme.AppTheme
import br.com.ajudafio.di.sharedModules
import br.com.ajudafio.presentation.book.di.bookUiModule
import br.com.ajudafio.presentation.book.list.BookListScreen
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(sharedModules + bookUiModule)
    }) {
        AppTheme {
            BookListScreen(
                onBookClick = {},
            )
        }
    }
}
