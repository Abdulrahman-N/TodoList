package com.example.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 2, exportSchema = false)
@androidx.room.TypeConverters(TypeConverters::class)
abstract class TodoListAppDatabase: RoomDatabase() {

    abstract fun todoListDao(): TodoListDao

}