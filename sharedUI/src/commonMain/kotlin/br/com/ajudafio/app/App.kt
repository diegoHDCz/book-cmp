package br.com.ajudafio.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import br.com.ajudafio.core.theme.AppTheme
import br.com.ajudafio.di.sharedModules
import br.com.ajudafio.presentation.book.SelectedBookViewModel
import br.com.ajudafio.presentation.book.detail.BookDetailAction
import br.com.ajudafio.presentation.book.detail.BookDetailScreenRoot
import br.com.ajudafio.presentation.book.detail.BookDetailViewModel
import br.com.ajudafio.presentation.book.di.bookUiModule
import br.com.ajudafio.presentation.book.list.BookListScreen
import br.com.ajudafio.presentation.book.list.BookListViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    KoinApplication(application = {
        modules(sharedModules + bookUiModule)
    }) {
        AppTheme {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Route.BookGraph
            ) {
                navigation<Route.BookGraph>(
                    startDestination = Route.BookList
                ) {
                    composable<Route.BookList> {
                        val viewModel = koinViewModel<BookListViewModel>()
                        val selectedBookViewModel =
                            it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                        LaunchedEffect(true) {
                            selectedBookViewModel.onSelectBook(null)
                        }

                        BookListScreen(
                            viewModel = viewModel,
                            onBookClick = { book ->
                                selectedBookViewModel.onSelectBook(book)
                                navController.navigate(Route.BookDetail(book.id))
                            }
                        )
                    }
                    composable<Route.BookDetail> {
                        val selectedBookViewModel =
                            it.sharedKoinViewModel<SelectedBookViewModel>(navController)
                        val viewModel = koinViewModel<BookDetailViewModel>()
                        val selectedBook by selectedBookViewModel.selectedBookDetail.collectAsStateWithLifecycle()

                        LaunchedEffect(selectedBook) {
                            selectedBook?.let {
                                viewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                            }
                        }

                        BookDetailScreenRoot(
                            viewModel = viewModel,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }

                }
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}