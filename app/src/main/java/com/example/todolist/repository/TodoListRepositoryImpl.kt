package com.example.todolist.repository

import com.example.todolist.data.Todo
import com.example.todolist.data.TodoListDao
import javax.inject.Inject

class TodoListRepositoryImpl @Inject constructor(private val todoListDao: TodoListDao) :
    TodoListRepository {

    override suspend fun insertTask(task: Todo) = todoListDao.insertTask(task)

    override suspend fun deleteTask(taskId: Int) = todoListDao.deleteTask(taskId)

    override suspend fun getTasks() = todoListDao.getTasks()
}