package com.andrey.appviewusers.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrey.appviewusers.R
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
    lateinit var usersAdapter: UsersAdapter

    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentViewUsersBinding =
        { inflater, container ->
            FragmentViewUsersBinding.inflate(inflater, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()


        viewModel.users.observe(viewLifecycleOwner, Observer { response ->
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

                viewModel.refreshUsers()

                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun setupRecyclerView() {

        usersAdapter = UsersAdapter {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.container_fragments, InfoUserFragment.newInstance(it.uuid))
                addToBackStack(null)
                commit()
            }
        }
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (usersAdapter.itemCount > 0
                        && !recyclerView.canScrollVertically(1)
                    ) {
                        showProgressBar()
                        viewModel.getUsers()
                        hideProgressBar()
                    }
                }

            })
        }

    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    companion object {

        fun newInstance() = ViewUsersFragment()

    }

}

