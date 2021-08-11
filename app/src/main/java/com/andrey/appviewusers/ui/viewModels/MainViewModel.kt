package com.andrey.appviewusers.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrey.appviewusers.db.User
import com.andrey.appviewusers.repository.UserRepository
import com.andrey.appviewusers.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val mutableUsers: MutableLiveData<Resource<List<User>>> = MutableLiveData()
    val users: LiveData<Resource<List<User>>> = mutableUsers
    private var checkRefresh: Boolean = false


    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            val users = mutableUsers.value?.data ?: listOf()
            mutableUsers.postValue(Resource.Loading())
            try {
                if (!checkRefresh) {
                    val newUserResponse = userRepository.getUsers()
                    mutableUsers.postValue(
                        Resource.Success(
                            users + newUserResponse
                        )
                    )
                    userRepository.insert(newUserResponse)
                } else {
                   userRepository.deleteDbUsers()
                    mutableUsers.postValue(null)
                    checkRefresh = false
                    getUsers()
                }
            } catch (error: Exception) {
                mutableUsers.postValue(Resource.Error(error.toString()))
            }
        }
    }

    fun refreshUsers(checkInternet: Boolean) {
        viewModelScope.launch {
            if (checkInternet) {
                checkRefresh = true
                getUsers()
            }
        }
    }


}

