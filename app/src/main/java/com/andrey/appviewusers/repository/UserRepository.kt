package com.andrey.appviewusers.repository

import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.utils.DiUtil.api


class UserRepository(private val appDatabase: AppDatabase) {


    suspend fun getUsers(pageNumber: Int) =
        api.getSomeData(pageNumber)

    suspend fun insert(users: List<Result>) = appDatabase?.userDao()?.insert(users) //suspend

    suspend fun getSavedUsers() = appDatabase?.userDao()?.getAll()

    suspend fun deleteDbUsers() = appDatabase?.userDao()?.deleteResults()

    suspend fun getUser(id: String) = appDatabase?.userDao()?.getUser(id)


}
