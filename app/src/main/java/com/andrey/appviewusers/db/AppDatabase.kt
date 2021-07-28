package com.andrey.appviewusers.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrey.appviewusers.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
