package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodoList")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val priority: Priority,
    val task: String,
    val taskDescription: String
)

enum class Priority{
    HIGH,
    MEDIUM,
    LOW
}