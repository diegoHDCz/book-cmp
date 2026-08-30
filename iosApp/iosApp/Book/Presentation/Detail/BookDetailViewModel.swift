import Foundation
import SharedLogic

// Espelha br.com.ajudafio.presentation.book.detail.BookDetailViewModel do sharedUI.
// O bookId chega via init (equivalente ao parametersOf do koinViewModel no Compose).
@MainActor
final class BookDetailViewModel: ObservableObject {
    @Published private(set) var state = BookDetailState()

    private let bookId: String

    init(bookId: String) {
        self.bookId = bookId
        load()
    }

    func onAction(_ action: BookDetailAction) {
        switch action {
        case .onRetry:
            load()
        case .onBackClick:
            break // navegação é responsabilidade da view host
        }
    }

    private func load() {
        state.isLoading = true
        state.errorMessage = nil
        Task {
            do {
                let book = try await BookGateway.shared.getBookById(id: bookId)
                state.isLoading = false
                state.book = book
            } catch let error as BookLoadException {
                state.isLoading = false
                state.errorMessage = error.message
            } catch {
                state.isLoading = false
                state.errorMessage = "Algo deu errado. Tente novamente."
            }
        }
    }
}
