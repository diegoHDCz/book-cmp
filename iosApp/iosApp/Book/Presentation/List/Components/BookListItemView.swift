import SwiftUI
import SharedLogic

// Espelha br.com.ajudafio.presentation.book.list.components.BookListItem do sharedUI.
struct BookListItemView: View {
    let book: Book
    let onClick: () -> Void

    var body: some View {
        Button(action: onClick) {
            HStack(alignment: .center, spacing: 16) {
                cover
                info
                Spacer(minLength: 0)
                Image(systemName: "chevron.right")
                    .font(.system(size: 20))
                    .foregroundColor(AppPallet.textColor)
            }
            .padding(16)
        }
        .buttonStyle(.plain)
        .background(AppPallet.primaryColorLight.opacity(0.2))
        .clipShape(RoundedRectangle(cornerRadius: 32))
    }

    @ViewBuilder
    private var cover: some View {
        let coverUrl = book.coverUrl.flatMap { URL(string: $0) }
        AsyncImage(url: coverUrl) { phase in
            switch phase {
            case .success(let image):
                image.resizable().aspectRatio(0.65, contentMode: .fit)
            case .empty:
                ProgressView()
            default:
                placeholderCover
            }
        }
        .frame(height: 100)
    }

    private var placeholderCover: some View {
        Image(systemName: "photo")
            .resizable()
            .aspectRatio(0.65, contentMode: .fit)
            .foregroundColor(AppPallet.textColorSecondary)
    }

    private var info: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(book.title)
                .font(.title3.weight(.medium))
                .lineLimit(2)
                .truncationMode(.tail)

            if let authorName = book.authors.first {
                Text(authorName)
                    .font(.body)
                    .lineLimit(1)
                    .truncationMode(.tail)
            }

            if let rating = book.averageRating?.doubleValue {
                HStack(spacing: 4) {
                    Text(String(format: "%.1f", (rating * 10).rounded() / 10.0))
                        .font(.subheadline)
                    Image(systemName: "star.fill")
                        .foregroundColor(.yellow)
                }
            }
        }
    }
}
