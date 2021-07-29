package com.andrey.appviewusers.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrey.appviewusers.model.UserResponse
import com.andrey.appviewusers.retrofit.Common
import com.andrey.appviewusers.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import com.andrey.appviewusers.model.Result

class MainViewModel: ViewModel() {

    val randomuserResults= MutableLiveData<List<Result>>()
    private val mService : RetrofitService by lazy {
        Common.retrofitService
    }

    init {
        getUserList()
    }


    private fun getUserList() {

        mService.getSomeData().enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Er","Error")
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
               // adapter.submitLisresponse.body()?.results)
                randomuserResults.value=response.body()?.results
            }

        })
    }

}
