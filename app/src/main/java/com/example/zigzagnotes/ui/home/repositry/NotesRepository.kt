package com.example.zigzagnotes.ui.home.repositry

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.room.dao.NoteDao
import com.example.zigzagnotes.room.database.DataBaseHelper
import com.example.zigzagnotes.room.database.DataBaseHelperImp
import com.example.zigzagnotes.util.ErrorResponse
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDao: NoteDao) {

    //Insert Data
    suspend fun insert(note: List<NoteModel>): ErrorResponse? {
        return try {
            notesDao.insertNotes(note)
            null
        } catch (e: Exception) {
            ErrorResponse(500, "Failed to insert note: ${e.message}")
        }
    }

    //Get Data
     fun getAll() = notesDao.getAllNotesData()

    //Delete data by Id
    suspend fun deleteById(id : Int) : ErrorResponse? {
        return try {
            notesDao.deleteById(id)
            null
        }catch (e : Exception){
            ErrorResponse(500, "Failed to delete note: ${e.message}")
        }
    }

    //Get Data by Id
    suspend fun getDataById(id :Int)=notesDao.getNotesById(id)

    //Update by Id
    suspend fun updateNotes(noteModel: NoteModel) :ErrorResponse?{
       return try {
           Log.d("Repository", "Note updated: $noteModel")
           notesDao.updateData(noteModel)
           null
       }catch (e : Exception){
           Log.e("Repository", "Failed to update note: ${e.message}", e)
           ErrorResponse(500, "Failed to update note: ${e.message}")
       }
    }

    //Delete All
    suspend fun deleteAll() : ErrorResponse? {
        return try {
            notesDao.deleteAllNotes()
            null
        }catch (e : Exception){
            Log.e("Repository", "Failed to delete note: ${e.message}", e)
            ErrorResponse(500, "Failed to update note: ${e.message}")
        }
    }
}
