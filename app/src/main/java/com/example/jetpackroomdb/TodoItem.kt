package com.example.jetpackroomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_todo_list")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Int = 0,

    @ColumnInfo(name = "item_name")
    val itemName: String,

    @ColumnInfo(name = "item_desk")
    val itemdesk: String,

    @ColumnInfo(name = "is_completed")
    var isDone: Boolean = false
)