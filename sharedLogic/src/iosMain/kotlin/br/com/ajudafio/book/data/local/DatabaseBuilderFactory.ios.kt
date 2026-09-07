package br.com.ajudafio.book.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

class DatabaseBuilderFactory {
    @OptIn(ExperimentalForeignApi::class)
    fun create(): RoomDatabase.Builder<BookDatabase> {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )?.path ?: error("Não foi possível localizar o diretório de documentos do iOS")

        return Room.databaseBuilder<BookDatabase>(
            name = "$documentDirectory/book.db"
        )
    }
}
