package com.andrey.appviewusers.repository

import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.db.User
import com.andrey.appviewusers.db.UserDao
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.model.toUser
import com.andrey.appviewusers.retrofit.RetrofitService
import com.andrey.appviewusers.utils.DiUtil.api


class UserRepository(
    private val userApi: RetrofitService,
    private val userDao: UserDao
    ) {


    suspend fun getUsers(pageNumber: Int): List<User>{
        val response = userApi.getSomeData(pageNumber)
        return if (response.isSuccessful) {
            response.body()?.results?.map { it.toUser() } ?: listOf()
        } else {
            userDao.getAll()
        }
    }

    suspend fun insert(users: List<User>) = userDao.insert(users)

    suspend fun getSavedUsers() = userDao.getAll()

    suspend fun deleteDbUsers() = userDao.deleteResults()

    suspend fun getUser(id: String) = userDao.getUser(id)


}
