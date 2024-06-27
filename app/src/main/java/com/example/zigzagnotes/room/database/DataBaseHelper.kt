package com.example.zigzagnotes.room.database

import com.example.zigzagnotes.model.NoteModel

interface DataBaseHelper {
    suspend fun insertAll(users: List<NoteModel>)
    suspend fun getAllNotes() : List<NoteModel>
    suspend fun deleteDataById(id : Int)
    suspend fun getNotesById(id : Int) : NoteModel
    suspend fun updateDataById(id: Int,title : String ,description : String)
    suspend fun deleteAllNotes(notes: List<NoteModel>)
}