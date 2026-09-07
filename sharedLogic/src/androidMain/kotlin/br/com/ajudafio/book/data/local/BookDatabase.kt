package br.com.ajudafio.book.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// No Android o Room ainda pode instanciar a implementação gerada via
// reflection (Class-based Room.databaseBuilder), então aqui não é preciso
// @ConstructedBy/expect-actual — só o Kotlin/Native (iOS) exige essa ponte,
// veja BookDatabase.kt em iosMain.
@Database(entities = [BookEntity::class], version = 1, exportSchema = true)
@TypeConverters(BookConverters::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
