package com.andrey.appviewusers.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.repository.UserRepository

class InfoUserViewModel:ViewModel() {

    val user: MutableLiveData<Result> = MutableLiveData<Result>()


    fun getUser(id: String) {
        UserRepository.getUser(id)?.let {
            user?.value = it
        }
    }
}
