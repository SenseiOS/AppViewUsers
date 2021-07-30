package com.andrey.appviewusers.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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

import androidx.lifecycle.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val PUT_ID_NAME = "id"

class MainActivity : AppCompatActivity() {

    lateinit var adapter: UsersAdapter

    private val viewModel: MainViewModel by lazy {
        createViewModel {
            MainViewModel(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startApplication()

        viewModel.randomuserResults.observe(this@MainActivity, Observer { adapter.submitList(it) })

    }

    private fun startApplication() {


        val rvItems = findViewById<View>(R.id.rvItems) as RecyclerView

        adapter = UsersAdapter {
            val infoIntent = Intent(this, InfoUser::class.java)

            infoIntent.putExtra(PUT_ID_NAME, it.login.uuid)

            this.startActivity(infoIntent)
        }

        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter

    }

}
