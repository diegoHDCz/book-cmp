package br.com.ajudafio.book.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters

// Kotlin/Native não tem reflection, então o Room precisa desta ponte
// expect/actual para instanciar a implementação gerada. O KSP (kspIosArm64 /
// kspIosSimulatorArm64) gera o `actual object BookDatabaseConstructor` para
// cada arquitetura automaticamente — não escreva o actual na mão.
@Database(entities = [BookEntity::class], version = 1, exportSchema = true)
@TypeConverters(BookConverters::class)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}

@Suppress("KotlinNoActualForExpect")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<BookDatabase> {
    override fun initialize(): BookDatabase
}
