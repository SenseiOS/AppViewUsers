package com.andrey.appviewusers.utils

import android.content.Context
import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.repository.UserRepository
import com.andrey.appviewusers.retrofit.RetrofitClient
import com.andrey.appviewusers.retrofit.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://randomuser.me/api/"

object DiUtil {

    private lateinit var contextProvider: () -> Context
    val userRepository by lazy {
        UserRepository(appDatabase)
    }
    private val appDatabase by lazy {
        AppDatabase(contextProvider())
    }

   private val retrofitService by lazy {
       getClient()
    }

    private fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val api by lazy {
        retrofitService.create(RetrofitService::class.java)
    }
    fun init(context: Context) {
        contextProvider = { context }
    }

}
