package com.andrey.appviewusers.ui.fragments

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrey.appviewusers.base.BaseFragment
import com.andrey.appviewusers.databinding.FragmentViewUsersBinding
import com.andrey.appviewusers.ui.adapter.UsersAdapter
import com.andrey.appviewusers.ui.viewModels.MainViewModel
import com.andrey.appviewusers.utils.DiUtil
import com.andrey.appviewusers.utils.Resource
import com.andrey.appviewusers.utils.createViewModel

class ViewUsersFragment : BaseFragment<FragmentViewUsersBinding>() {

    private val viewModel: MainViewModel by lazy {
        createViewModel {
            MainViewModel(DiUtil.userRepository)
        }
    }

    private val usersAdapter: UsersAdapter by lazy{
         UsersAdapter(
            clickListener = {
                replaceFragment(InfoUserFragment.newInstance(it.uuid))
            },
            paginationListener = {
                showProgressBar()
                viewModel.getUsers()
                hideProgressBar()

            }
         )
    }

    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentViewUsersBinding =
        { inflater, container ->
            FragmentViewUsersBinding.inflate(inflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.users.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data.let { usersResponse ->
                        usersAdapter.submitList(usersResponse?.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("App", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.refreshUsers(isOnline())
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }

    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    companion object {
        fun newInstance() = ViewUsersFragment()
    }

}

