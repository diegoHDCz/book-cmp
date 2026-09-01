package br.com.ajudafio.presentation.book.detail

import androidx.lifecycle.ViewModel
import br.com.ajudafio.book.domain.model.Book
import br.com.ajudafio.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookDetailViewModel(
    private val bookRepository: BookRepository,
) : ViewModel() {

    private val _selectedBookDetail = MutableStateFlow<Book?>(null)
    val selectedBookDetail = _selectedBookDetail.asStateFlow()

    fun onSelectBook(book: Book?) {
        _selectedBookDetail.value = book
    }
}
