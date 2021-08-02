package com.andrey.appviewusers.retrofit

import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("?results=20&inc=email,login,username,name,location,gender,dob,picture")
    fun getSomeData(@Query("page") page: Int?):UserResponse
}
