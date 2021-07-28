package com.andrey.appviewusers.retrofit

private val BASE_URL = "https://randomuser.me/api/"

object Common {

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)

}