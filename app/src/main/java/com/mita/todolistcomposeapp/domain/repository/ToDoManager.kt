package com.mita.todolistcomposeapp.domain.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.mita.todolistcomposeapp.data.model.ToDoModel
import java.time.Instant
import java.util.Date

object ToDoManager {

    private val todoList = mutableListOf<ToDoModel>()

    fun getAllToDoList(): List<ToDoModel> {
        return todoList
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDoItem(title: String) {
        todoList.add(ToDoModel(System.currentTimeMillis().toInt(), title,
            Date.from(Instant.now())
        ))

    }

    fun deleteToDoItem(id: Int) {
        todoList.removeAll{ it.id == id }

    }

    fun updateToDoItem(updatedTodo: ToDoModel) {
        todoList.replaceAll { if (it.id == updatedTodo.id) updatedTodo else it }
    }
}