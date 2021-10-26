package com.example.mymovies.ui.discover

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieNew::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieNewDao() : MovieNewDAO
}

