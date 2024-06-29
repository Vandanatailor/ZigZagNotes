package com.example.zigzagnotes.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters

@Entity
@TypeConverters(NoteTypeConverter::class)

data class NoteModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var notes: Notes
)
