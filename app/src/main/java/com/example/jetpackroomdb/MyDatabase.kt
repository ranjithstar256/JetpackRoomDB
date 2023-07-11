package com.example.jetpackroomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// MyDatabase.kt
@Database(entities = [TodoItem::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): TodoDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "abcdfxd.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

