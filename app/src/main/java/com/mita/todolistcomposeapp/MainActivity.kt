package com.mita.todolistcomposeapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.mita.todolistcomposeapp.presentation.ToDoListPage
import com.mita.todolistcomposeapp.presentation.ToDoViewModel
import com.mita.todolistcomposeapp.ui.theme.ToDoListComposeAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toDoViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        //  enableEdgeToEdge()
        setContent {
            ToDoListComposeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ToDoListPage(toDoViewModel)
                }
            }
        }
    }


}
