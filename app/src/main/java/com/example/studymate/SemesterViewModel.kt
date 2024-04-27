package com.example.studymate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SemesterViewModel : ViewModel() {


    private var _semesters = MutableStateFlow<List<Semesters>>(emptyList())
    val semesters: StateFlow<List<Semesters>> = _semesters

    init {
        fetchSubjects()
    }

    private fun fetchSubjects(){
        viewModelScope.launch {
            val repo = SemesterRepository()
            val response = repo.getSemesters()
            _semesters.value = response
        }
    }
}