package com.example.jetpackroomdb

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.jetpackroomdb.ui.theme.JetpackRoomDBTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            JetpackRoomDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    lateinit var todoDao: TodoDatabaseDao

                    var db: TodoDatabase =
                        Room.inMemoryDatabaseBuilder(applicationContext, TodoDatabase::class.java)
                            .allowMainThreadQueries()
                            .build()
                    todoDao = db.todoDao()

                    var r = false
                    Column {

                        var s1 by remember {
                            mutableStateOf("abc")
                        }
                        var s2 by remember {
                            mutableStateOf("xyz")
                        }

                        TextField(value = s1, onValueChange = { s1 = it })
                        TextField(value = s2, onValueChange = { s2 = it })

                        Button(onClick = {
                            r = true
                        s2=s1}) {
                            Text(text = "Save")

                        }
                        if (r) {
                            val todoItem = TodoItem(itemName = s2, isDone = false)
                            val scope = rememberCoroutineScope()
                            scope.launch {
                                todoDao.insert(todoItem)
                            }
                        } else{
                            val todoItem = TodoItem(itemName = s2, isDone = false)
                            val scope = rememberCoroutineScope()
                            scope.launch {
                                todoDao.insert(todoItem)
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun Greeting(todoDatabaseDao: TodoDatabaseDao) {

        val scope = rememberCoroutineScope()
        scope.launch {

            try {

                val todoItem = TodoItem(itemName = "some Item", isDone = false)
                todoDatabaseDao.insert(todoItem)

                // var g = todoDatabaseDao.getById(1)
                //  todoDatabaseDao.deleteAllTodos()

                //    var c = g?.itemName
                //    Log.i("dddffdsfd", "item is : " + c)

            } catch (ex: Exception) {
                println("cancelled")
            }
        }
    }
}