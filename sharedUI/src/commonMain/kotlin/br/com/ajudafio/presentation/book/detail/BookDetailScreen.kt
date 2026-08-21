package br.com.ajudafio.presentation.book.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.ajudafio.feature.book.domain.model.Book
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * O bookId chega por parâmetro e é repassado ao ViewModel via Koin
 * (parametersOf), então o VM não precisa de SavedStateHandle pra isso.
 */
@Composable
fun BookDetailScreen(
    bookId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookDetailViewModel = koinViewModel { parametersOf(bookId) },
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookDetailContent(
        state = state,
        onAction = { action ->
            if (action is BookDetailAction.OnBackClick) {
                onBackClick()
            }
            viewModel.onAction(action)
        },
        modifier = modifier,
    )
}

@Composable
private fun BookDetailContent(
    state: BookDetailState,
    onAction: (BookDetailAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier.fillMaxSize()) {
        when {
            state.isLoading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }

            state.errorMessage != null -> Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            ) {
                Text(
                    text = state.errorMessage.asString(),
                    color = MaterialTheme.colorScheme.error,
                )
                Spacer(Modifier.height(8.dp))
                Button(onClick = { onAction(BookDetailAction.OnRetry) }) {
                    Text("Tentar novamente")
                }
            }

            state.book != null -> BookDetailInfo(book = state.book)
        }
    }
}

@Composable
private fun BookDetailInfo(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .safeContentPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text(text = book.title, style = MaterialTheme.typography.headlineSmall)
        if (book.authors.isNotEmpty()) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = book.authors.joinToString(),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        val description = book.description
        if (!description.isNullOrBlank()) {
            Spacer(Modifier.height(16.dp))
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
