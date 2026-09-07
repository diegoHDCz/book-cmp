package br.com.ajudafio.presentation.book

import androidx.lifecycle.ViewModel
import br.com.ajudafio.book.domain.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedBookViewModel(
) : ViewModel() {

    private val _selectedBookDetail = MutableStateFlow<Book?>(null)
    val selectedBookDetail = _selectedBookDetail.asStateFlow()

    fun onSelectBook(book: Book?) {
        _selectedBookDetail.value = book
    }
}