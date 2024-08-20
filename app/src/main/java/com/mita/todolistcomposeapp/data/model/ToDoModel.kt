package com.mita.todolistcomposeapp.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity(tableName = "todo_table")
data class ToDoModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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