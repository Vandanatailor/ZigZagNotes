package com.example.zigzagnotes.ui.home.repositry

import androidx.lifecycle.LiveData
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.room.dao.NoteDao
import com.example.zigzagnotes.room.database.DataBaseHelper
import com.example.zigzagnotes.room.database.DataBaseHelperImp
import com.example.zigzagnotes.util.ErrorResponse
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDao: NoteDao) {
    suspend fun insert(note: List<NoteModel>): ErrorResponse? {
        return try {
            notesDao.insertNotes(note)
            null
        } catch (e: Exception) {
            ErrorResponse(500, "Failed to insert note: ${e.message}")
        }
    }
     fun getAll() = notesDao.getAllNotesData()

    suspend fun deleteById(id : Int) : ErrorResponse? {
        return try {
            notesDao.deleteById(id)
            null
        }catch (e : Exception){
            ErrorResponse(500, "Failed to insert note: ${e.message}")
        }
    }

}
