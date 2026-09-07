package br.com.ajudafio.book.data.local

import br.com.ajudafio.book.domain.model.Book

fun BookEntity.toBook() = Book(
    id = id,
    title = title,
    authors = authors,
    description = description,
    coverUrl = coverUrl,
    languages = languages,
    firstPublishYear = firstPublishYear,
    averageRating = averageRating,
    ratingCount = ratingCount,
    numPages = numPages,
    numEditions = numEditions
)

fun Book.toEntity() = BookEntity(
    id = id,
    title = title,
    authors = authors,
    description = description,
    coverUrl = coverUrl,
    languages = languages,
    firstPublishYear = firstPublishYear,
    averageRating = averageRating,
    ratingCount = ratingCount,
    numPages = numPages,
    numEditions = numEditions
)
