package br.com.ajudafio.presentation.book.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookListViewModel(
//    private val searchBooks: SearchBooksUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        BookListState(
            searchResults = books,
        )
    )
    val state: StateFlow<BookListState> = _state.asStateFlow()

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnBookClick -> {
                TODO()
            }
            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is BookListAction.onTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }

}
