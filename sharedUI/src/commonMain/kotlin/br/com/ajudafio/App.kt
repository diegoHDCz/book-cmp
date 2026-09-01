package br.com.ajudafio

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.ajudafio.core.theme.AppTheme
import br.com.ajudafio.di.sharedModules
import br.com.ajudafio.presentation.book.di.bookUiModule
import br.com.ajudafio.presentation.book.list.BookListScreen
import org.koin.compose.KoinApplication
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import br.com.ajudafio.app.Route
import br.com.ajudafio.presentation.book.detail.BookDetailScreen
import br.com.ajudafio.presentation.book.list.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    KoinApplication(application = {
        modules(sharedModules + bookUiModule)
    }) {
        AppTheme {
            val navController= rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Route.BookGraph
            ) {
                navigation<Route.BookGraph>(
                    startDestination = Route.BookList
                ) {
                    composable<Route.BookList> {
                        val viewModel = koinViewModel<BookListViewModel>()
                        BookListScreen(
                            viewModel = viewModel,
                            onBookClick = { book ->
                                navController.navigate(Route.BookDetail(book.id))
                            }
                        )
                    }
                    composable<Route.BookDetail> { backStackEntry ->
                        val route = backStackEntry.toRoute<Route.BookDetail>()
                        BookDetailScreen(
                            bookId = route.id,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
