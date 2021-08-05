package com.andrey.appviewusers.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrey.appviewusers.R
import com.andrey.appviewusers.ui.activities.MainActivity
import com.andrey.appviewusers.ui.adapter.UsersAdapter
import com.andrey.appviewusers.ui.viewModels.MainViewModel
import com.andrey.appviewusers.utils.Resource
import com.andrey.appviewusers.databinding.FragmentViewUsersBinding

class ViewUsersFragment : Fragment(R.layout.fragment_view_users) {

    lateinit var viewModel: MainViewModel
    lateinit var usersAdapter: UsersAdapter

    private var viewBinding: FragmentViewUsersBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewBinding = FragmentViewUsersBinding.bind(view)

        setupRecyclerView()


    viewModel.users.observe(viewLifecycleOwner, Observer { response ->
        when (response) {
            is Resource.Success -> {
                hideProgressBar()
                response.data?.let { usersResponse ->
                    usersAdapter.submitList(usersResponse.results.toList())
                }
            }
            is Resource.Error -> {
                hideProgressBar()
                response.message?.let { message ->
                    Log.e("App", "An error occured: $message")
                }
                //adapter.submitList(viewModel.getSavedUser())
            }
            is Resource.Loading -> {
                showProgressBar()
            }
        }
    })

        viewBinding!!.swipeRefreshLayout.setOnRefreshListener {

        viewModel.refreshUsers()

            viewBinding!!.swipeRefreshLayout.isRefreshing = false
    }
}

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

private fun setupRecyclerView() {

    usersAdapter = UsersAdapter {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.container_fragments, InfoUserFragment.newInstance(it.login.uuid))
            addToBackStack(null)
            commit()
        }
    }
    viewBinding!!.rvUsers.apply {
        layoutManager = LinearLayoutManager(activity)
        adapter = usersAdapter
        addOnScrollListener(this@ViewUsersFragment.scrollListener)
    }

}

private fun hideProgressBar() {
    viewBinding!!.paginationProgressBar.visibility = View.INVISIBLE
    isLoading = false
}

private fun showProgressBar() {
    viewBinding!!.paginationProgressBar.visibility = View.VISIBLE
    isLoading = true
}

var isLoading = false
var isScrolling = false

private val scrollListener = object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount

        val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
        val isNotAtBeginning = firstVisibleItemPosition >= 0
        val shouldPaginate = isAtLastItem && isNotAtBeginning && isScrolling
        if (shouldPaginate) {
            viewModel.getUsers()
            isScrolling = false
        } else {
            recyclerView.setPadding(0, 0, 0, 0)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true
        }
    }
}

    companion object {

        fun newInstance() = ViewUsersFragment()

    }

}

