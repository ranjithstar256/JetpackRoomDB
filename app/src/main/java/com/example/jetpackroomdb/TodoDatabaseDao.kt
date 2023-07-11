package com.example.jetpackroomdb

 import androidx.room.Dao
 import androidx.room.Insert
 import androidx.room.Query

@Dao
interface TodoDatabaseDao {

    @Insert
    suspend fun d(item:TodoItem)
    @Query("SELECT * FROM my_todo_list")
    suspend fun getUsers(): List<TodoItem>

}