package com.mita.todolistcomposeapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mita.todolistcomposeapp.model.ToDoModel

class ToDoViewModel : ViewModel() {
    private var _todoItems = MutableLiveData<List<ToDoModel>>()
    val todoItems: LiveData<List<ToDoModel>> = _todoItems

    private fun getAllToDoList(){
        _todoItems.value= ToDoManager.getAllToDoList().reversed()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDoItem(title: String){
        ToDoManager.addToDoItem(title)
        getAllToDoList()

    }

    fun deleteToDoItem(int: Int){
        ToDoManager.deleteToDoItem(int)
        getAllToDoList()

    }

    fun updateToDoItem(){

    }
}