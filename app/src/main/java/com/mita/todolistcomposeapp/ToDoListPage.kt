package com.mita.todolistcomposeapp

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mita.todolistcomposeapp.model.ToDoModel
import com.mita.todolistcomposeapp.model.getDemoToDo
import java.text.SimpleDateFormat
import java.util.Locale

@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToDoListPage(viewModel: ToDoViewModel) {
    //val todoList = getDemoToDo()
    val context = LocalContext.current.applicationContext
    val todoList by viewModel.todoItems.observeAsState()
    val focusManager = LocalFocusManager.current
    var inputText by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Row {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        text = "ToDo List",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                })
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = {
                        inputText = it
                    },
                    label = {
                        Text(
                            text = "Enter item title",
                            color = Color.Black,
                            fontSize = 16.sp
                        )

                    },
                    textStyle = TextStyle(color = Color.Black),
                    maxLines = 1,

                    )
                Button(
                    onClick = {
                        if (inputText.isNotEmpty()) {
                            viewModel.addToDoItem(inputText)
                            inputText = ""
                            focusManager.clearFocus()
                        } else {
                            Toast.makeText(context, "Please enter a tile", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }) {
                    Text(
                        text = "Add",
                        color = Color.White
                    )
                }
            }
            todoList?.let {
                LazyColumn(content = {
                    itemsIndexed(it) { _: Int, item: ToDoModel ->
                        ToDoItem(item = item, onDelete = {
                            viewModel.deleteToDoItem(item.id)
                        })

                    }
                })
            } ?: Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                textAlign = TextAlign.Center,
                text = "No items yet",
                fontSize = 20.sp,
                color = Color.Black
            )

        }
    }

}

@Composable
fun ToDoItem(item: ToDoModel, onDelete: () -> Unit) {
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
                SimpleDateFormat("HH:mm:aa, dd/MM/yyyy", Locale.ENGLISH).format(item.createdAt)
            Text(
                text = time,
                fontSize = 14.sp,
                color = Color.Cyan
            )
            Text(
                text = item.title,
                fontSize = 18.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete_icons),
                contentDescription = R.string.app_name.toString(),
                tint = Color.White
            )

        }
    }

}
