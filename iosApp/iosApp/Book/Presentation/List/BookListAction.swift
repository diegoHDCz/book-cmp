import SharedLogic

// Espelha br.com.ajudafio.presentation.book.list.BookListAction do sharedUI.
enum BookListAction {
    case onSearchQueryChange(String)
    case onBookClick(Book)
    case onTabSelected(Int)
}
