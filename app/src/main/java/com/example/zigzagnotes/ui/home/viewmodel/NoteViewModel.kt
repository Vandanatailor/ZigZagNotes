package com.example.zigzagnotes.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.ui.home.repositry.NotesRepository
import com.example.zigzagnotes.util.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {



    private val _errorResponse = MutableLiveData<ErrorResponse?>()
    val errorResponse: LiveData<ErrorResponse?> get() = _errorResponse

    val alNotes : LiveData<List<NoteModel>> get() = MutableLiveData()

    fun insert(note: List<NoteModel>) = viewModelScope.launch {
        _errorResponse.value = repository.insert(note)
    }

}


