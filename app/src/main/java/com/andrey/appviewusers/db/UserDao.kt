package com.andrey.appviewusers.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrey.appviewusers.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM Users")
    fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
}
