package com.example.zigzagnotes.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.room.dao.NoteDao

@Database(entities = [NoteModel::class], version = 2, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NoteDao
}

object DatabaseBuilder {
//    private var INSTANCE: NotesDatabase? = null
//
//    fun getInstance(context: Context): NotesDatabase {
//        if (INSTANCE == null) {
//            synchronized(NotesDatabase::class) {
//                if (INSTANCE == null) {
//                    INSTANCE = buildRoomDB(context)
//                }
//            }
//        }
//        return INSTANCE!!
//    }

//    private fun buildRoomDB(context: Context) =
//        Room.databaseBuilder(
//            context.applicationContext,
//            NotesDatabase::class.java,
//            "Notes"
//        ).addMigrations(MIGRATION_1_2)
//            .build()
}

//private val MIGRATION_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        // Create the new table with the updated schema
//        database.execSQL("""
//            CREATE TABLE NoteModel_new (
//                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
//                notes TEXT NOT NULL
//            )
//        """.trimIndent())
//
//        // Copy data from the old table to the new table
//        database.execSQL("""
//            INSERT INTO NoteModel_new (id, notes)
//            SELECT id, '{"title": "", "description": notes}' FROM NoteModel
//        """)
//
//    }
//}