package com.example.zigzagnotes.ui.home.repositry

import androidx.lifecycle.LiveData
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.room.database.DataBaseHelper
import com.example.zigzagnotes.room.database.DataBaseHelperImp
import com.example.zigzagnotes.util.ErrorResponse

class NotesRepository (private val noteDataBaseHelper: DataBaseHelperImp)
   {

    suspend fun insert(note: List<NoteModel>): ErrorResponse? {
    return try {
        noteDataBaseHelper.insertAll(note)
        null
    } catch (e: Exception) {
        ErrorResponse(500, "Failed to insert note: ${e.message}")
    }

}

}