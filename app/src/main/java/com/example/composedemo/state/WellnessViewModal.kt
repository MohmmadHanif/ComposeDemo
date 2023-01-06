package com.example.composedemo.state

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModal : ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val task = _tasks


    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }
}

private fun getWellnessTasks() = List(30) { WellnessTask(it, "Task #$it") }