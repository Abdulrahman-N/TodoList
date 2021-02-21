package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Todo
import com.example.todolist.repository.TodoListRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repository: TodoListRepositoryImpl) :
    ViewModel() {

    private val _tasks = MutableStateFlow<List<Todo>>(emptyList())
    val tasks: StateFlow<List<Todo>>
    get() = _tasks

    lateinit var selectedTask: Todo
    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            repository.getTasks().collectLatest {
                _tasks.value = it
            }

        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
        }
    }

    fun insertTask(task: Todo) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

}