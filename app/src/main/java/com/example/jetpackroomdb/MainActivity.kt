package com.example.jetpackroomdb


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.jetpackroomdb.ui.theme.JetpackRoomDBTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
     @SuppressLint("CoroutineCreationDuringComposition")
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackRoomDBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val db =   TodoDatabase.getInstance(applicationContext).todoDao()
                    Column() {

                        Log.i("jvjghvhjvh", "onCreate: ")

                        var s by remember { mutableStateOf(false) }
                        var ret by remember { mutableStateOf(false) }
                        if (ret == true) {
                            val scope = rememberCoroutineScope()

                            scope.launch {

                                try {

                                 
                                        db.d(TodoItem(itemName = "some new data",itemdesk="all well", isDone = false))

                                } catch (ex: Exception) {
                                    println("cancelled")
                                }
                            }

                        }

                        Button(onClick = { ret = true }) {
                            Text(text = "get data")

                        }

                    }
                }
            }
        }
    }
}
/*

@Composable
fun MyComposableFunction(context: Context) {
    val database = TodoDatabase.getInstance(context)
    val myDao = database.myDao()

    val nameState = remember { mutableStateOf("") }

    Column {
        // Text field to enter the name
        TextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Name") }
        )

        // Button to insert the data
        Button(
            onClick = {
                val name = nameState.value
                if (name.isNotBlank()) {
                    // Insert the entity into the database
                    viewModelScope.launch {
                        myDao.insert(MyEntity(name = name))
                    }
                    // Clear the text field
                    nameState.value = ""
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Insert")
        }
    }
}*/
