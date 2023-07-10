package com.example.jetpackroomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDatabaseDao {

    @Insert
    suspend fun d(item:TodoItem)


}