package com.example.zigzagnotes.ui.home.viewmodel

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

    fun getAll() = viewModelScope.launch(Dispatchers.IO) {
        _listNotData.postValue(repository.getAll())
    }

    fun insert(note: List<NoteModel>) = viewModelScope.launch {
        _errorResponse.value = repository.insert(note)
    }

    fun deleteById(id : Int) =viewModelScope.launch(Dispatchers.Main) {
        _errorResponse.value=repository.deleteById(id)
    }

}


