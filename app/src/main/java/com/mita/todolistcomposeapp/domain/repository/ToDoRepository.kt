package com.mita.todolistcomposeapp.domain.repository

import com.mita.todolistcomposeapp.data.db.ToDoDao
import com.mita.todolistcomposeapp.data.model.ToDoModel

class ToDoRepository(private val todoDao: ToDoDao) {
    suspend fun getAllTodos(): List<ToDoModel> {
        return todoDao.getAllToDo()
    }

    suspend fun insert(todo: ToDoModel) {
        todoDao.insert(todo)
    }

    suspend fun update(todo: ToDoModel) {
        todoDao.update(todo)
    }

    suspend fun delete(todo: ToDoModel) {
        todoDao.delete(todo)
    }
}