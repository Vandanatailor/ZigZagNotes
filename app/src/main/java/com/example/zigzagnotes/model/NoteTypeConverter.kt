package com.example.zigzagnotes.model

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class NoteTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun fromNotes(notes: Notes): String {
        return gson.toJson(notes)
    }

    @TypeConverter
    fun toNotes(notesString: String): Notes {
        return Gson().fromJson(notesString, Notes::class.java)
    }
}