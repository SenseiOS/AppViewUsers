package com.andrey.appviewusers.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM Users")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM Users WHERE id = :id")
    suspend fun getUser(id: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>)

    @Query("DELETE FROM Users")
    suspend fun deleteResults()

}
