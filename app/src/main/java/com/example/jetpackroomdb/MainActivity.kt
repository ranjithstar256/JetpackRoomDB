package com.example.jetpackroomdb


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.jetpackroomdb.ui.theme.JetpackRoomDBTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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
                    val database = MyDatabase.getDatabase(applicationContext)
                    val myDao = database.myDao()

                    Column() {
                        var ret by remember { mutableStateOf(false) }
                        var gt by remember { mutableStateOf(false) }
                        if (ret) {
                            val scope = rememberCoroutineScope()
                            scope.launch {

                                try {
                                    myDao.d(
                                        TodoItem(
                                            itemName = "some new data",
                                            itemdesk = "all well",
                                            isDone = false
                                        )
                                    )
                                } catch (ex: Exception) {
                                    println("cancelled")
                                }
                            }
                        }

                        if (gt) {
                            // Retrieve users from the database
                            val todoitemsrows by remember {
                                derivedStateOf {
                                    runBlocking {
                                        withContext(Dispatchers.IO) {
                                            database.myDao().getUsers()
                                        }
                                    }
                                }
                            }

                            todoitemsrows.forEach {
                                Text("${it.itemName} - ${it.itemdesk}")
                            }
                        }

                        Button(onClick = { ret = !ret }) {
                            Text(text = "get data")
                        }

                        Button(onClick = { gt = !gt }) {
                            Text(text = "Retrive data")
                        }


                    }
                }
            }
        }
    }
}
