package com.andrey.appviewusers.ui.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.repository.UserRepository

class MainViewModel(context: Context): ViewModel() {

    lateinit var randomuserResults: MutableLiveData<List<Result>>


    init {
        getUsers(context)
    }

    fun getUsers(context: Context) {

        UserRepository.getUserList(context)

        randomuserResults = UserRepository.randomuserResults
    }





}
