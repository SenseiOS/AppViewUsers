package com.andrey.appviewusers.retrofit

import com.andrey.appviewusers.model.User
import com.andrey.appviewusers.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("?page=1&results=10&inc=login,username,name,location,gender,dob,picture")
    fun getSomeData(): Call<UserResponse>
}