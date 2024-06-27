package com.example.zigzagnotes.room.database

import com.example.zigzagnotes.model.NoteModel

class DataBaseHelperImp(private val notesDatabase: NotesDatabase) : DataBaseHelper {
    override suspend fun insertAll(users: List<NoteModel>) = notesDatabase.notesDao().insertNotes(users)
    override suspend fun getAllNotes(): List<NoteModel> =notesDatabase.notesDao().getAllNotesData()
    override suspend fun deleteDataById(id: Int) =notesDatabase.notesDao().deleteById(id)
    override suspend fun getNotesById(id: Int): NoteModel = notesDatabase.notesDao().getNotesById(id)
    override suspend fun updateDataById(id: Int, title: String, description: String) = notesDatabase.notesDao().updateNoteById(id,title,description)

}