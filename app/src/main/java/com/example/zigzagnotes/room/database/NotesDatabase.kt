package com.example.zigzagnotes.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.room.dao.NoteDao

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NoteDao
}

object DatabaseBuilder {
    private var INSTANCE: NotesDatabase? = null

    fun getInstance(context: Context): NotesDatabase {
        if (INSTANCE == null) {
            synchronized(NotesDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = buildRoomDB(context)
                }
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            NotesDatabase::class.java,
            "Notes"
        ).build()
}