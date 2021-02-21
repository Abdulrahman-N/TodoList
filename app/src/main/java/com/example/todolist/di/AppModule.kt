package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.TodoListAppDatabase
import com.example.todolist.data.TodoListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): TodoListAppDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                TodoListAppDatabase::class.java,
                "TODO_LIST_DATABASE"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTodoListDao(db: TodoListAppDatabase): TodoListDao {
        return db.todoListDao()
    }
}
