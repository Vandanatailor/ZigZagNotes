package com.example.zigzagnotes.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.zigzagnotes.model.NoteModel

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNotes(notesModel: List<NoteModel>)

    @Query("SELECT * FROM notemodel")
    //suspend
    fun getAllNotesData(): List<NoteModel>

    @Query("SELECT * FROM notemodel WHERE id = :id LIMIT 1")
    suspend fun getNotesById(id : Int): NoteModel

    @Delete
    fun deleteAllNotes(notesallDelete: List<NoteModel>)

    @Query("DELETE FROM notemodel WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Update
    suspend fun updateData(notesModel: NoteModel)

//    @Query("UPDATE notemodel SET title = :title," +
//            " description = :description WHERE id = :id")
//    suspend fun updateNoteById(id: Int, title: String, description: String)

}