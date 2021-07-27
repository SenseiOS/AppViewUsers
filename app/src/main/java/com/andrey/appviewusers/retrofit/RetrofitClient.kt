package com.andrey.appviewusers.retrofit

import com.andrey.appviewusers.interfeces.ApiService
import retrofit2.Retrofit
import retrofit2.create

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String) : Retrofit {
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .build()
        }
        return retrofit!!
    }
}
