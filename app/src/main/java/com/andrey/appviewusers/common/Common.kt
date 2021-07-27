package com.andrey.appviewusers.common

import com.andrey.appviewusers.interfeces.RetrofitService
import com.andrey.appviewusers.retrofit.RetrofitClient

private val BASE_URL = "https://randomuser.me/api/"

object Common {

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}
