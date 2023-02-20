package com.example.jetpackroomdb

import androidx.compose.material.Text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RoomDBViewModel( private val dbHelper: TodoDatabase) :
    ViewModel() {

    init {
        fetchCourses()
    }

    private fun fetchCourses() {
        viewModelScope.launch {
            try {
                val CoursesFromDb = dbHelper.todoDao().getAll()
                // here you have your CoursesFromDb
            } catch (e: Exception) {
                // handler error
            }
        }
    }
}