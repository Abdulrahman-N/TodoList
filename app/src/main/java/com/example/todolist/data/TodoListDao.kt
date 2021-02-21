package com.example.todolist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListDao {

    @Insert
    suspend fun insertTask(task: Todo)

    @Query("DELETE FROM TodoList WHERE id = :taskId")
    suspend fun deleteTask(taskId: Int)

    @Query("SELECT * FROM TodoList")
    fun getTasks(): Flow<List<Todo>>

}