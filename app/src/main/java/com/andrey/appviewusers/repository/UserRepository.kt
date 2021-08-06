package com.andrey.appviewusers.repository

import com.andrey.appviewusers.db.User
import com.andrey.appviewusers.db.UserDao
import com.andrey.appviewusers.model.toUser
import com.andrey.appviewusers.retrofit.RetrofitService


class UserRepository(
    private val userApi: RetrofitService,
    private val userDao: UserDao
) {

    private var trueConnection = false
    private var usersPage = 1

    suspend fun getUsers(): List<User> {
        try {
            val response = userApi.getSomeData(usersPage)

            usersPage++
            if (!trueConnection) {
                trueConnection = true
                deleteDbUsers()
            }
            return response.body()?.results?.map { it.toUser() } ?: listOf()
        } catch (error: Exception) {
        }

        trueConnection = false
        return getSavedUsers()
    }

    suspend fun insert(users: List<User>) = userDao.insert(users)

    private suspend fun getSavedUsers() = userDao.getAll()

    private suspend fun deleteDbUsers() = userDao.deleteResults()

    suspend fun getUser(id: String) = userDao.getUser(id)


}
