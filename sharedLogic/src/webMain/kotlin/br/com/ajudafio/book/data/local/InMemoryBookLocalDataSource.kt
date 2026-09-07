package br.com.ajudafio.book.data.local

import br.com.ajudafio.book.domain.model.Book

// Room não publica artefatos para js/wasmJs, então a Web usa um cache
// em memória atrás do mesmo contrato BookLocalDataSource.
class InMemoryBookLocalDataSource : BookLocalDataSource {
    private val cache = mutableMapOf<String, Book>()

    override suspend fun getBookById(id: String): Book? = cache[id]

    override suspend fun upsertBook(book: Book) {
        cache[book.id] = book
    }
}
