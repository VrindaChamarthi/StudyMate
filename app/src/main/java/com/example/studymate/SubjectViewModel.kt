package com.example.studymate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubjectspageViewModel:ViewModel() {


    private var _subjects = MutableStateFlow<List<resource>>(emptyList())
    val subjects: StateFlow<List<resource>> = _subjects
    init{
        fetchCategories()
    }
    private fun fetchCategories(){
        viewModelScope.launch {
            val repo = subjectRepository()
            val response = repo.getCategories(subject)
            _subjects.value = response
        }
    }
}