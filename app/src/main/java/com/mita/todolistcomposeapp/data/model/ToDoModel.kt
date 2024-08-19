package com.mita.todolistcomposeapp.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date

data class ToDoModel(
    var id: Int,
    var title: String,
    var createdAt: Date,
) /*{
    constructor() : this(0, "", Date())
}*/


@RequiresApi(Build.VERSION_CODES.O)
fun getDemoToDo(): List<ToDoModel> {
    return listOf<ToDoModel>(
        ToDoModel(1, "Demo ToDo 1", Date.from(Instant.now())),
        ToDoModel(2, "Demo ToDo 2", Date.from(Instant.now())),
        ToDoModel(3, "Demo ToDo 3", Date.from(Instant.now())),
        ToDoModel(4, "Demo ToDo 4", Date.from(Instant.now())),
    )
}