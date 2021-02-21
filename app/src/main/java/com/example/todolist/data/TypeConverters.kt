package com.example.todolist.data

import androidx.room.TypeConverter

class TypeConverters {

    @TypeConverter
    fun fromPriority(priority: Priority) = priority.name
    @TypeConverter
    fun toPriority(string: String) = Priority.valueOf(string)
}