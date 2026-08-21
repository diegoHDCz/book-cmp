package br.com.ajudafio.presentation.book.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ajudafio.core.domain.onError
import br.com.ajudafio.core.domain.onSuccess
import br.com.ajudafio.feature.book.domain.usecase.GetBookDetailsUseCase
import br.com.ajudafio.presentation.book.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val bookId: String,
    private val getBookDetails: GetBookDetailsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(BookDetailState())
    val state: StateFlow<BookDetailState> = _state.asStateFlow()

    init {
        load()
    }

    fun onAction(action: BookDetailAction) {
        when (action) {
            BookDetailAction.OnRetry -> load()
            BookDetailAction.OnBackClick -> Unit // navegação é responsabilidade do host
        }
    }

    private fun load() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            getBookDetails(bookId)
                .onSuccess { book ->
                    _state.update { it.copy(isLoading = false, book = book) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.toUiText()) }
                }
        }
    }
}
