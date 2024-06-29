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
       // return "${notes.title},${notes.description}"
    }

    @TypeConverter
    fun toNotes(notesString: String): Notes {
//        val type = object : TypeToken<Notes>() {}.type
//        return gson.fromJson(notesString, type)
        return try {
            gson.fromJson(notesString, Notes::class.java)
        } catch (e: JsonSyntaxException) {

           // Handle parsing exception
            Notes("   ", "")

           // Log.d("NoteTypeConverter", "Failed to convert JSON string to Notes object", e)
           // null
        }
    }
}