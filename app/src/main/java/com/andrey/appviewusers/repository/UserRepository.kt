package com.andrey.appviewusers.repository

import android.content.Context
import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.retrofit.Common
import com.andrey.appviewusers.retrofit.RetrofitService


object UserRepository {

    var appDatabase: AppDatabase? = null
    private val mService: RetrofitService by lazy {
        Common.retrofitService
    }

    fun initialDb(context: Context) {
        appDatabase = AppDatabase.invoke(context)
    }

    suspend fun getUsers(pageNumber: Int) =
        mService.getSomeData(pageNumber)

    fun insert(users: List<Result>) = appDatabase?.userDao()?.insert(users) //suspend

    fun getSavedUsers() = appDatabase?.userDao()?.getAll()

    fun deleteDbUsers() = appDatabase?.userDao()?.deleteResults()

    fun getUser(id: String) = appDatabase?.userDao()?.getUser(id)


}
