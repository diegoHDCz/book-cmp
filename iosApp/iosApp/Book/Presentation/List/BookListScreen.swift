import SwiftUI
import SharedLogic

// Espelha br.com.ajudafio.presentation.book.list.BookListScreen do sharedUI:
// StateObject faz o papel de koinViewModel(), o resto é (state, onAction) puro.
struct BookListScreen: View {
    @StateObject private var viewModel = BookListViewModel()
    let onBookClick: (Book) -> Void

    var body: some View {
        BookListContent(state: viewModel.state) { action in
            if case .onBookClick(let book) = action {
                onBookClick(book)
            }
            viewModel.onAction(action)
        }
    }
}

private struct BookListContent: View {
    let state: BookListState
    let onAction: (BookListAction) -> Void

    var body: some View {
        ZStack {
            AppPallet.primaryColorLight.ignoresSafeArea(edges: .top)

            VStack(spacing: 0) {
                BookSearchBar(
                    searchQuery: state.searchQuery,
                    onSearchQueryChange: { onAction(.onSearchQueryChange($0)) }
                )
                .frame(maxWidth: 400)
                .padding(.horizontal, 16)
                .padding(.vertical, 36)

                VStack(spacing: 4) {
                    Picker(
                        "",
                        selection: Binding(
                            get: { state.selectedTabIndex },
                            set: { onAction(.onTabSelected($0)) }
                        )
                    ) {
                        Text("Search Results").tag(0)
                        Text("Favorites").tag(1)
                    }
                    .pickerStyle(.segmented)
                    .frame(maxWidth: 700)
                    .padding(.horizontal, 16)
                    .padding(.vertical, 12)

                    TabView(
                        selection: Binding(
                            get: { state.selectedTabIndex },
                            set: { onAction(.onTabSelected($0)) }
                        )
                    ) {
                        searchResultsPage.tag(0)
                        favoritesPage.tag(1)
                    }
                    .tabViewStyle(.page(indexDisplayMode: .never))
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(AppPallet.backgroundColor)
                .clipShape(
                    .rect(topLeadingRadius: 32, bottomLeadingRadius: 0, bottomTrailingRadius: 0, topTrailingRadius: 32)
                )
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }

    @ViewBuilder
    private var searchResultsPage: some View {
        Group {
            if state.isLoading {
                ProgressView()
            } else if let errorMessage = state.errorMessage {
                messageText(errorMessage)
            } else if state.searchResults.isEmpty {
                messageText("Oops.. there aren't any item like this search")
            } else {
                BookListView(books: state.searchResults) { onAction(.onBookClick($0)) }
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }

    @ViewBuilder
    private var favoritesPage: some View {
        Group {
            if state.favoriteBooks.isEmpty {
                messageText("You haven't saved any favorite books yet.")
            } else {
                BookListView(books: state.favoriteBooks) { onAction(.onBookClick($0)) }
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }

    private func messageText(_ text: String) -> some View {
        Text(text)
            .multilineTextAlignment(.center)
            .font(.headline)
            .foregroundColor(AppPallet.accentColor)
            .padding(.horizontal, 24)
    }
}
