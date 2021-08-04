package com.andrey.appviewusers.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrey.appviewusers.model.Result

@Dao
interface UserDao {

    @Query("SELECT * FROM Users")
    fun getAll(): List<Result>

    @Query("SELECT * FROM Users WHERE id = :id")
    fun getUser(id: String): Result

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<Result>)

    @Query("DELETE FROM Users")
    fun deleteResults()

}
