package com.andrey.appviewusers.db

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrey.appviewusers.model.Result

@Dao
interface UserDao {

    @Query("SELECT * FROM Users")
    fun getAll(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: MutableLiveData<List<Result>>)

    @Query("DELETE FROM Users")
    fun deleteResults()

    //@Query("SELECT * FROM Users WHERE   = :id")
    //fun getById(id: Int): Result?

}
