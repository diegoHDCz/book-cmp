package br.com.ajudafio.feature.book.data.mapper

import br.com.ajudafio.feature.book.data.remote.dto.BookDto
import br.com.ajudafio.feature.book.domain.model.Book

/**
 * Fronteira entre data e domain: DTO nunca vaza pra fora da data.
 */
fun BookDto.toBook(): Book = Book(
    id = id,
    title = title,
    authors = authors,
    description = description,
    coverUrl = coverUrl,
    languages = TODO(),
    firstPublishYear = TODO(),
    averageRating = TODO(),
    ratingCount = TODO(),
    numPages = TODO(),
    numEditions = TODO(),
)
