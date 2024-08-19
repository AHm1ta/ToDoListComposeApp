package com.mita.todolistcomposeapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mita.todolistcomposeapp.domain.repository.ToDoManager
import com.mita.todolistcomposeapp.data.model.ToDoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ToDoViewModel : ViewModel() {
    private var _todoItems = MutableStateFlow<List<ToDoModel>>(emptyList())
    val todoItems: StateFlow<List<ToDoModel>> = _todoItems

    private var _selectedTodo: ToDoModel? = null

    var isEditing = MutableStateFlow(false) // Track if we are in edit mode

    init {
        getAllToDoList()
    }

    private fun getAllToDoList() {
        viewModelScope.launch {
            _todoItems.value = ToDoManager.getAllToDoList().reversed()
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDoItem(title: String) {
        viewModelScope.launch {
            ToDoManager.addToDoItem(title)
            getAllToDoList()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addOrUpdateTodo(title: String) {
        viewModelScope.launch {
            val todoToUpdate = _selectedTodo
            if (todoToUpdate != null) {
                // Update the existing todo
                ToDoManager.updateToDoItem(
                    todoToUpdate.copy(
                        title = title
                    )
                )
            } else {
                // Add a new todo
                ToDoManager.addToDoItem(title)
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

    fun deleteToDoItem(int: Int) {
        viewModelScope.launch {
            ToDoManager.deleteToDoItem(int)
            getAllToDoList()
        }

    }

}