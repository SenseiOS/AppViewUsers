package com.andrey.appviewusers.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrey.appviewusers.R
import com.andrey.appviewusers.model.UserResponse
import com.andrey.appviewusers.retrofit.Common
import com.andrey.appviewusers.retrofit.RetrofitService
import com.andrey.appviewusers.ui.adapter.UsersAdapter
import com.andrey.appviewusers.ui.viewModels.MainViewModel
import com.andrey.appviewusers.utils.createViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var adapter: UsersAdapter

    lateinit var mService : RetrofitService

    private val viewModel: MainViewModel by lazy {
        createViewModel {
            MainViewModel()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewModel.liveItems.observe(this@MainActivity, Observer { adapter.submitList(it) })
        startApplication()

        getUserList()
    }

    private fun startApplication() {

        mService = Common.retrofitService

        val rvItems = findViewById<View>(R.id.rvItems) as RecyclerView

        adapter = UsersAdapter {
            val infoIntent = Intent(this, InfoUser::class.java)

            infoIntent.putExtra(PUT_ID_NAME, it.login.uuid)

            this.startActivity(infoIntent)
        }

        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter

    }

    private fun getUserList() {

        mService.getSomeData().enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Er","Error")
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                adapter.submitList(response.body()?.results)
            }

        })
    }


    companion object{
        private const val PUT_ID_NAME = "id"
    }
}
