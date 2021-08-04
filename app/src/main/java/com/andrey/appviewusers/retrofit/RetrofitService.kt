package com.andrey.appviewusers.retrofit

import com.andrey.appviewusers.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("?results=20&inc=email,login,username,name,location,gender,dob,picture")
    suspend fun getSomeData(@Query("page") page: Int?): Response<UserResponse>
}
