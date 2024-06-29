package com.example.zigzagnotes.model

import androidx.room.TypeConverter

class NoteTypeConverter {

    @TypeConverter
    fun fromNotes(notes: Notes): String {
        return "${notes.title},${notes.description}"
    }

    @TypeConverter
    fun toNotes(notesString: String): Notes {
        val (title, description) = notesString.split(",")
        return Notes(title, description)
    }
}