package com.example.zigzagnotes.room.database

import com.example.zigzagnotes.model.NotesModel

class DataBaseHelperImp(private val notesDatabase: NotesDatabase) : DataBaseHelper {
    override suspend fun insertAll(users: List<NotesModel>) = notesDatabase.notesDao().insertNotes(users)
}