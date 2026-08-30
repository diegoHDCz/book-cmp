import SwiftUI

// Espelha br.com.ajudafio.presentation.book.list.components.BookSearchBar do sharedUI.
struct BookSearchBar: View {
    let searchQuery: String
    let onSearchQueryChange: (String) -> Void

    var body: some View {
        HStack(spacing: 8) {
            Image(systemName: "magnifyingglass")
                .foregroundColor(AppPallet.textColor.opacity(0.66))

            TextField(
                "Search...",
                text: Binding(get: { searchQuery }, set: onSearchQueryChange)
            )
            .textFieldStyle(.plain)

            if !searchQuery.isEmpty {
                Button {
                    onSearchQueryChange("")
                } label: {
                    Image(systemName: "xmark.circle.fill")
                        .foregroundColor(AppPallet.textColor)
                }
                .transition(.opacity.animation(.easeInOut(duration: 0.3)))
            }
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 12)
        .background(AppPallet.backgroundColor)
        .clipShape(Capsule())
        .overlay(
            Capsule().stroke(AppPallet.secondaryColorLight, lineWidth: 1)
        )
        .animation(.easeInOut(duration: 0.3), value: searchQuery.isEmpty)
    }
}
