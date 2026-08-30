import SharedLogic

// Espelha br.com.ajudafio.presentation.book.detail.BookDetailState do sharedUI.
struct BookDetailState {
    var isLoading: Bool = false
    var book: Book?
    var errorMessage: String?
}
