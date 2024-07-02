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
