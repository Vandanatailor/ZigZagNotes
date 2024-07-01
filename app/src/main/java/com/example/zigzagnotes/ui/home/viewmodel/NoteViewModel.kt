package com.example.zigzagnotes.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.ui.home.repositry.NotesRepository
import com.example.zigzagnotes.util.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

    private val _errorResponse = MutableLiveData<ErrorResponse?>()
    val errorResponse: LiveData<ErrorResponse?> get() = _errorResponse

    private val _listNotData =MutableLiveData<List<NoteModel>>()
    val listNotData : LiveData<List<NoteModel>> get() = _listNotData

    private val _getNotesById =MutableLiveData<NoteModel>()

    val getNoteData : LiveData<NoteModel> get()=_getNotesById


    fun getAll() = viewModelScope.launch(Dispatchers.IO) {
        _listNotData.postValue(repository.getAll())
    }

    fun insert(note: List<NoteModel>) = viewModelScope.launch {
        _errorResponse.value = repository.insert(note)
    }

    fun deleteById(id : Int) =viewModelScope.launch(Dispatchers.Main) {
        _errorResponse.value=repository.deleteById(id)
    }

    fun getDataById(id : Int) =viewModelScope.launch(Dispatchers.IO) {
        _getNotesById.postValue(repository.getDataById(id))
    }

    fun updateNotes(noteModel: NoteModel)=viewModelScope.launch(Dispatchers.IO){
        Log.d("ViewModel", "Updating note: $noteModel")
        _errorResponse.postValue(repository.updateNotes(noteModel))
    }

     fun deleteAllNotes()=viewModelScope.launch(Dispatchers.IO){
         _errorResponse.postValue(repository.deleteAll())
    }

}


