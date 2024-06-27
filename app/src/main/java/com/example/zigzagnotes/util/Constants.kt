package com.example.zigzagnotes.util

class Constants {
    companion object{

        const val Delete ="Delete"
        const val NotesStore="NotesStore"
        const val DataShow="DataShow"
        const val ID="ID"
        const val Title="Title"
        const val Description="Description"

    }
}

interface ItemsCLickListner{
    fun selectItemCLick(position :Int, type: String)
}