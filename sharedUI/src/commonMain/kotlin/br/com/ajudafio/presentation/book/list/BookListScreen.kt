package br.com.ajudafio.presentation.book.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.ajudafio.feature.book.domain.model.Book
import br.com.ajudafio.presentation.book.list.components.BookSearchBar
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BookListScreen(
    onBookClick: (Book) -> Unit,
    viewModel: BookListViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListContent(
        state = state,
        onAction = { action ->
            when (action) {
                is BookListAction.OnBookClick -> onBookClick(action.book)
                else -> Unit
            }
            viewModel.onAction(action)
        },

        )
}

@Composable
private fun BookListContent(
    state: BookListState,
    onAction: (BookListAction) -> Unit,

    ) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding().padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(BookListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                OutlinedTextField(
//                    value = state.query,
//                    onValueChange = { onAction(BookListAction.OnQueryChange(it)) },
//                    label = { Text("Buscar livros") },
//                    singleLine = true,
//                    modifier = Modifier.weight(1f),
//                )
//                Spacer(Modifier.width(8.dp))
//                Button(onClick = { onAction(BookListAction.OnSearchClick) }) {
//                    Text("Buscar")
//                }
//            }
//
//            Spacer(Modifier.height(16.dp))
//
//            when {
//                state.isLoading -> Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center,
//                ) {
//                    CircularProgressIndicator()
//                }
//
//                state.errorMessage != null -> Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center,
//                ) {
//                    Text(
//                        text = state.errorMessage.asString(),
//                        color = MaterialTheme.colorScheme.error,
//                    )
//                }
//
//                else -> LazyColumn(
//                    verticalArrangement = Arrangement.spacedBy(8.dp),
//                    modifier = Modifier.fillMaxSize(),
//                ) {
//                    items(items = state.books, key = { it.id }) { book ->
//                        BookListItem(
//                            book = book,
//                            onClick = { onAction(BookListAction.OnBookClick(book.id)) },
//                        )
//                    }
//                }
//            }
    }

}


@Composable
private fun BookListItem(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            if (book.authors.isNotEmpty()) {
                Text(
                    text = book.authors.joinToString(),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
