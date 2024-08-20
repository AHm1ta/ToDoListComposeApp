package com.mita.todolistcomposeapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mita.todolistcomposeapp.data.model.ToDoModel
import com.mita.todolistcomposeapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {
    private var _todoItems = MutableStateFlow<List<ToDoModel>>(emptyList())
    val todoItems: StateFlow<List<ToDoModel>> = _todoItems

    private var _selectedTodo: ToDoModel? = null

    var isEditing = MutableStateFlow(false) // Track if we are in edit mode

    init {
        getAllToDoList()
    }

    private fun getAllToDoList() {
        viewModelScope.launch {
           // _todoItems.value = ToDoManager.getAllToDoList().reversed()
            //room
            _todoItems.value = repository.getAllTodos().reversed()
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDoItem(title: String) {
        viewModelScope.launch {
            repository.insert(ToDoModel(title = title, createdAt = Date()))
            getAllToDoList()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addOrUpdateTodo(title: String) {
        viewModelScope.launch {
            val todoToUpdate = _selectedTodo
            if (todoToUpdate != null) {
                // Update the existing todo
                /*repository.updateToDoItem(
                    todoToUpdate.copy(
                        title = title
                    )
                )*/
                //room
                repository.update(
                    todoToUpdate.copy(
                        title = title
                    )
                )
            } else {
                // Add a new todo
                //ToDoManager.addToDoItem(title)
                //room
                repository.insert(ToDoModel(title = title, createdAt = Date()))
            }
            _selectedTodo = null
            isEditing.value = false // Exit edit mode
            getAllToDoList()
        }
    }

    fun selectTodoForEditing(todo: ToDoModel) {
        _selectedTodo = todo
        isEditing.value = true
    }

    fun clearSelectedTodo() {
        _selectedTodo = null
        isEditing.value = false
    }

    fun getSelectedTodo(): ToDoModel? = _selectedTodo

    fun deleteToDoItem(todo: ToDoModel) {
        viewModelScope.launch {
            repository.delete(todo)
            getAllToDoList()
        }

    }

}