package com.andrey.appviewusers.db

import android.content.Context
import androidx.room.Room

object RoomClient {

    fun provideAppDatabase( context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "randomUser_database")
            .build()
    }

}
