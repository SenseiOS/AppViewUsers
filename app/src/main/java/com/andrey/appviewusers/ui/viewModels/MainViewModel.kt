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
    val user: MutableLiveData<User>? = null
    private var userResponse: MutableList<User>? = null


    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            mutableUsers.postValue(Resource.Loading())
            try {
                val response = userRepository.getUsers()
                if (userResponse == null) {
                    userResponse = response.toMutableList()
                } else {
                    userResponse?.addAll(response)

                }

                userResponse?.let { mutableUsers.postValue(Resource.Success(it)) }
                saveUsers(response)
            } catch (error: Exception) {
                mutableUsers.postValue(Resource.Error(error.toString()))
            }
        }
    }

    private suspend fun saveUsers(users: List<User>) =
        userRepository.insert(users)


    fun refreshUsers() {
        userResponse = null

        getUsers()
    }

}
