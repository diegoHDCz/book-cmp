package br.com.ajudafio.book.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class DatabaseBuilderFactory(private val context: Context) {
    fun create(): RoomDatabase.Builder<BookDatabase> {
        val dbFile = context.applicationContext.getDatabasePath("book.db")
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = BookDatabase::class.java,
            name = dbFile.absolutePath
        )
    }
}
