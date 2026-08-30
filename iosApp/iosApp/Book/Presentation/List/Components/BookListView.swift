import SwiftUI
import SharedLogic

// Espelha br.com.ajudafio.presentation.book.list.components.BookList do sharedUI.
struct BookListView: View {
    let books: [Book]
    let onBookClick: (Book) -> Void

    var body: some View {
        ScrollView {
            LazyVStack(spacing: 12) {
                ForEach(books, id: \.id) { book in
                    BookListItemView(book: book) {
                        onBookClick(book)
                    }
                    .frame(maxWidth: 700)
                    .padding(.horizontal, 16)
                }
            }
            .padding(.vertical, 12)
        }
    }
}
