package br.com.ajudafio.book.data.mappers

import br.com.ajudafio.book.data.dto.SearchedBookDto
import br.com.ajudafio.book.domain.model.Book

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id,
        title = title,
        authors = authorNames.orEmpty(),
        description = null,
        coverUrl = coverAlternativeKey?.let { "https://covers.openlibrary.org/b/id/$it-L.jpg" },
        languages = languages.orEmpty(),
        firstPublishYear = firstPublishYear?.toString(),
        averageRating = ratingAverage,
        ratingCount = ratingsCount,
        numPages = numberPagesMedian,
        numEditions = numEditions ?: 0
    )
}