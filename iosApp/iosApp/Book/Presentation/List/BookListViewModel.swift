import Foundation
import SharedLogic

// Espelha br.com.ajudafio.presentation.book.list.BookListViewModel do sharedUI:
// mesmo state/action, mas debounce via Task.sleep no lugar de Flow.debounce,
// já que não temos Kotlin Coroutines/Flow do lado Swift.
@MainActor
final class BookListViewModel: ObservableObject {
    @Published private(set) var state = BookListState()

    private var cachedBooks: [Book] = []
    private var searchTask: Task<Void, Never>?

    func onAction(_ action: BookListAction) {
        switch action {
        case .onSearchQueryChange(let query):
            state.searchQuery = query
            scheduleSearch(query: query)

        case .onTabSelected(let index):
            state.selectedTabIndex = index

        case .onBookClick:
            break // navegação é responsabilidade da view host
        }
    }

    private func scheduleSearch(query: String) {
        searchTask?.cancel()
        searchTask = Task { [weak self] in
            try? await Task.sleep(nanoseconds: 500_000_000)
            guard let self, !Task.isCancelled else { return }
            await self.runSearch(query: query)
        }
    }

    private func runSearch(query: String) async {
        if query.isEmpty {
            state.errorMessage = nil
            state.searchResults = cachedBooks
            return
        }
        guard query.count >= 2 else { return }

        state.isLoading = true
        do {
            let results = try await BookGateway.shared.searchBooks(query: query)
            state.isLoading = false
            state.errorMessage = nil
            state.searchResults = results
        } catch let error as BookLoadException {
            state.isLoading = false
            state.searchResults = []
            state.errorMessage = error.message
        } catch {
            state.isLoading = false
            state.searchResults = []
            state.errorMessage = "Algo deu errado. Tente novamente."
        }
    }
}
