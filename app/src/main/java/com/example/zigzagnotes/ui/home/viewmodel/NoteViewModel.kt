package com.example.zigzagnotes.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.ui.home.repositry.NotesRepository
import com.example.zigzagnotes.util.ErrorResponse
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NotesRepository) : ViewModel() {
    private val _errorResponse = MutableLiveData<ErrorResponse?>()
    val errorResponse: LiveData<ErrorResponse?> get() = _errorResponse
    fun insert(note: List<NoteModel>) = viewModelScope.launch {
        _errorResponse.value = repository.insert(note)
    }
}