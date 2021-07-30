package com.andrey.appviewusers.ui.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.repository.UserRepository

class MainViewModel(context: Context): ViewModel() {

    var randomuserResults: MutableLiveData<List<Result>>


    init {
        UserRepository.getUserList(context)

        randomuserResults = UserRepository.randomuserResults
    }





}
