package com.andrey.appviewusers.db

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM Users WHERE id = :id")
    fun getUser(id:String):Result

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<Result>)

    @Query("DELETE FROM Users")
    fun deleteResults()

    //@Query("SELECT * FROM Users WHERE   = :id")
    //fun getById(id: Int): Result?

   // @Query("SELECT COUNT(id) from Users")
   // suspend fun count(): Int

}
