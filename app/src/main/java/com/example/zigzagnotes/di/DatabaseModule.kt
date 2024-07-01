package com.example.zigzagnotes.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.zigzagnotes.room.dao.NoteDao
import com.example.zigzagnotes.room.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext : Context) : NotesDatabase {
      return  Room.databaseBuilder(
            appContext.applicationContext,
            NotesDatabase::class.java,
            "Notes"
        ).addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideNoteDao(database: NotesDatabase): NoteDao {
        return database.notesDao()
    }
}

private val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the new table with the updated schema
        database.execSQL("""
            CREATE TABLE NoteModel_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                notes TEXT NOT NULL
            )
        """.trimIndent())

        // Copy data from the old table to the new table
        database.execSQL("""
            INSERT INTO NoteModel_new (id, notes)
            SELECT id, '{"title": "", "description": notes}' FROM NoteModel
        """)

    }
}
