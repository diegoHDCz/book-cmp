import SharedLogic

// Espelha br.com.ajudafio.presentation.book.list.BookListState do sharedUI.
struct BookListState {
    var searchQuery: String = ""
    var searchResults: [Book] = []
    var favoriteBooks: [Book] = []
    var isLoading: Bool = false
    var selectedTabIndex: Int = 0
    var errorMessage: String?
}
