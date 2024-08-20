package com.mita.todolistcomposeapp.presentation

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mita.todolistcomposeapp.R
import com.mita.todolistcomposeapp.data.model.ToDoModel
import java.text.SimpleDateFormat
import java.util.Locale


@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToDoListPage(viewModel: ToDoViewModel) {
    //val todoList = getDemoToDo()
    val context = LocalContext.current.applicationContext
    val todoList by viewModel.todoItems.collectAsState()
    val focusManager = LocalFocusManager.current
    val isEditing by viewModel.isEditing.collectAsState()
    var inputText by remember {
        mutableStateOf("")
    }

    // Update the input fields if a todo is selected for editing
    LaunchedEffect(viewModel.getSelectedTodo()) {
        val selectedTodo = viewModel.getSelectedTodo()
        if (selectedTodo != null) {
            inputText = selectedTodo.title

        }
    }


    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Row {
            TopAppBar(modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        text = "ToDo List", color = Color.White, fontSize = 20.sp
                    )
                })
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(2.5f),
                    value = inputText,
                    onValueChange = {
                        inputText = it
                    },
                    label = {
                        Text(text = "Enter item title", color = Color.Black, fontSize = 16.sp)
                    },
                    textStyle = TextStyle(color = Color.Black),
                    singleLine = true,
                    keyboardActions = KeyboardActions(onDone = {
                        if (inputText.isNotBlank()) {
                            viewModel.addOrUpdateTodo(inputText)
                            inputText = ""
                        }
                    }),
                    // imeAction = ImeAction.Done
                )
                Button(modifier = Modifier
                    .weight(1.2f)
                    .padding(5.dp), onClick = {
                    if (inputText.isNotBlank()) {
                        viewModel.addOrUpdateTodo(inputText)
                        inputText = ""
                        focusManager.clearFocus()
                        viewModel.clearSelectedTodo()
                    } else {
                        Toast.makeText(context, "Please enter a tile", Toast.LENGTH_SHORT)
                            .show()
                    }

                }) {
                    Text(
                        text = if (isEditing) "Update" else "Add",
                        color = Color.White,
                        maxLines = 1,
                        fontSize = 12.sp
                    )
                }

            }


            if (todoList.isNotEmpty()) {
                TodoList(todos = todoList,
                    onDeleteClick = { todo -> viewModel.deleteToDoItem(todo) },
                    onEditClick = { todo -> viewModel.selectTodoForEditing(todo) })
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(), Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "No items add yet",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }

}

@Composable
fun TodoList(
    todos: List<ToDoModel>,
    onDeleteClick: (ToDoModel) -> Unit,
    onEditClick: (ToDoModel) -> Unit?,
) {
    LazyColumn(Modifier.fillMaxHeight(), content = {
        itemsIndexed(todos) { index: Int, item: ToDoModel ->
            ToDoItem(item = item,
                onDelete = { onDeleteClick(item) },
                onEdit = { onEditClick(item) })
            //divider add
            /*if (index != it.size - 1) {
            Divider()
        }*/

        }
    })


}

@Composable
fun ToDoItem(item: ToDoModel, onDelete: () -> Unit, onEdit: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(6.dp)
        ) {
            val time =
                SimpleDateFormat("hh:mm:aa, dd/MM/yyyy", Locale.ENGLISH).format(item.createdAt)
            Text(
                text = time, fontSize = 14.sp, color = Color.Cyan
            )
            Text(
                text = item.title, fontSize = 18.sp, color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete_icons),
                contentDescription = R.string.app_name.toString(),
                tint = Color.White
            )

        }
        IconButton(onClick = onEdit) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit_icon),
                contentDescription = R.string.app_name.toString(),
                tint = Color.White
            )

        }
    }

}
