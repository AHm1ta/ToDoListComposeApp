package com.mita.todolistcomposeapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mita.todolistcomposeapp.data.model.ToDoModel

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    suspend fun getAllToDo(): List<ToDoModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDoModel: ToDoModel)

    @Update
    suspend fun update(toDoModel: ToDoModel)

    @Delete
    suspend fun delete(toDoModel: ToDoModel)
}