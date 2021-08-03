package com.andrey.appviewusers.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.ProgressBar
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.andrey.appviewusers.utils.Resource
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

    private lateinit var paginationProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startApplication()

        paginationProgressBar = findViewById(R.id.paginationProgressBar)
        viewModel.users.observe(this@MainActivity, Observer { response ->
            when (response) {
                is Resource.Success -> {
                   hideProgressBar()
                    response.data?.let { usersResponse ->
                        adapter.submitList(usersResponse.results)
                        //val totalPages = usersResponse.totalResults / QUERY_PAGE_SIZE + 2
                        //isLastPage = viewModel.breakingNewsPage == totalPages
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("123", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                   showProgressBar()
                }
            }
        })

            //adapter.submitList(it.data?.results) })

/*            val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
            swipeRefreshLayout .setOnRefreshListener {

                //viewModel.getUsers(baseContext)

                swipeRefreshLayout.isRefreshing = false
            }*/
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
        rvItems.addOnScrollListener(this.scrollListener)

    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            //val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            //val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate =isAtLastItem && isNotAtBeginning && isScrolling
            if(shouldPaginate) {
                viewModel.getUsers()
                isScrolling = false
            } else {
                recyclerView.setPadding(0, 0, 0, 0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

}
