package com.andrey.appviewusers.utils

import android.content.Context
import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.repository.UserRepository
import com.andrey.appviewusers.retrofit.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api by lazy {
        retrofitService.create(RetrofitService::class.java)
    }

    fun init(context: Context) {
        contextProvider = { context }
    }

}
