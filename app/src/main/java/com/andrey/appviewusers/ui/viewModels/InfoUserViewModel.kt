package com.andrey.appviewusers.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrey.appviewusers.db.User
import com.andrey.appviewusers.repository.UserRepository
import kotlinx.coroutines.launch

class InfoUserViewModel(
    private val userRepository: UserRepository,
    private val userId: String
) : ViewModel() {

    private val mutableUser: MutableLiveData<User> = MutableLiveData<User>()
    val user: LiveData<User> = mutableUser


    fun getUser(){
        viewModelScope.launch {
            userRepository.getUser(userId).let {
                mutableUser.value = it
            }
        }
    }
}
