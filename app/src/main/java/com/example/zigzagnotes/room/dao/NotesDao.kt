package com.example.zigzagnotes.room.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.zigzagnotes.model.NotesModel

@Dao
interface NotesDao {

  @Insert
   suspend fun insertNotes(notesModel: List<NotesModel>)

}