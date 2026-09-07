package br.com.ajudafio.book.data.local

import br.com.ajudafio.book.domain.model.Book

class RoomBookLocalDataSource(
    private val bookDao: BookDao
) : BookLocalDataSource {
    override suspend fun getBookById(id: String): Book? {
        return bookDao.getById(id)?.toBook()
    }

    override suspend fun upsertBook(book: Book) {
        bookDao.upsert(book.toEntity())
    }
}
