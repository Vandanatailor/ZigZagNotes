package com.example.zigzagnotes.room.database

import com.example.zigzagnotes.model.NotesModel

interface DataBaseHelper {
    suspend fun insertAll(users: List<NotesModel>)

}