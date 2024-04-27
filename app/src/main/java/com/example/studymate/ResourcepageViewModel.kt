package com.example.studymate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResourcepageViewModel() :ViewModel() {
    private var _resources = MutableStateFlow<List<Resource>>(emptyList())
    val resources: StateFlow<List<Resource>> = _resources

    fun fetchResources(subjectTitle: String){
        viewModelScope.launch {try {
            val repo = resourceRepository()
            val response = repo.getResources(subjectTitle)
            _resources.value = response
        } catch (ex: Exception) {
            Log.e("book",ex.message,ex)
        }
        }
    }
}
