package com.andrey.appviewusers.interfeces

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET("?page=1&results=10&seed=abc")
    fun getSomeData(): Call<DataResponse>

    @POST(“udpdateData”)
    fun updateSomeData(@RequestBody newData: Data): Call<UpdateResponse>
}
