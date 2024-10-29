package com.example.todoapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> = _tasks

    fun addTask(name: String) {
        val newTask = Task(id = _tasks.size + 1, name = name)
        _tasks.add(newTask)
    }

    fun removeTask(task: Task) {
        _tasks.remove(task)
    }

    fun toggleTaskCompletion(task: Task) {
        task.isCompleted = !task.isCompleted
    }
}