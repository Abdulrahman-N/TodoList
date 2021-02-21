package com.example.todolist.repository

import com.example.todolist.data.Todo
import kotlinx.coroutines.flow.Flow

interface TodoListRepository {

    suspend fun insertTask(task: Todo)
    suspend fun deleteTask(taskId: Int)
    suspend fun getTasks(): Flow<List<Todo>>

}