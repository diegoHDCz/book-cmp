import SwiftUI
import SharedLogic

// Espelha br.com.ajudafio.presentation.book.detail.BookDetailScreen do sharedUI.
struct BookDetailScreen: View {
    let bookId: String
    let onBackClick: () -> Void
    @StateObject private var viewModel: BookDetailViewModel

    init(bookId: String, onBackClick: @escaping () -> Void) {
        self.bookId = bookId
        self.onBackClick = onBackClick
        _viewModel = StateObject(wrappedValue: BookDetailViewModel(bookId: bookId))
    }

    var body: some View {
        BookDetailContent(state: viewModel.state) { action in
            if case .onBackClick = action {
                onBackClick()
            }
            viewModel.onAction(action)
        }
        .navigationTitle(viewModel.state.book?.title ?? "")
        .navigationBarTitleDisplayMode(.inline)
    }
}

private struct BookDetailContent: View {
    let state: BookDetailState
    let onAction: (BookDetailAction) -> Void

    var body: some View {
        Group {
            if state.isLoading {
                ProgressView()
            } else if let errorMessage = state.errorMessage {
                VStack(spacing: 12) {
                    Text(errorMessage)
                        .multilineTextAlignment(.center)
                        .foregroundColor(AppPallet.accentColor)
                    Button("Tentar novamente") {
                        onAction(.onRetry)
                    }
                }
                .padding(24)
            } else if let book = state.book {
                BookDetailInfo(book: book)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

private struct BookDetailInfo: View {
    let book: Book

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 4) {
                Text(book.title)
                    .font(.title2.weight(.semibold))

                if !book.authors.isEmpty {
                    Text(book.authors.joined(separator: ", "))
                        .font(.headline)
                        .foregroundColor(AppPallet.textColorSecondary)
                }

                if let description = book.description_, !description.isEmpty {
                    Spacer().frame(height: 16)
                    Text(description)
                        .font(.body)
                }
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(16)
        }
    }
}
