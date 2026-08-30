import SwiftUI

struct ContentView: View {
    @State private var path: [String] = []

    var body: some View {
        NavigationStack(path: $path) {
            BookListScreen(onBookClick: { book in
                path.append(book.id)
            })
            .navigationDestination(for: String.self) { bookId in
                BookDetailScreen(
                    bookId: bookId,
                    onBackClick: { path.removeLast() }
                )
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
