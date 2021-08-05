package com.andrey.appviewusers.ui.viewModels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrey.appviewusers.db.User
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.model.UserResponse
import com.andrey.appviewusers.repository.UserRepository
import com.andrey.appviewusers.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
   private val userRepository: UserRepository
) : ViewModel() {


    val users: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    var usersPage = 1
    var userResponse: UserResponse? = null


    init {
        getUsers()
    }

    fun getUsers() = viewModelScope.launch {
        users.postValue(Resource.Loading())
       // if (isOnline(context)) {
            val response = userRepository.getUsers(usersPage)
          //  response.body()?.let { userRepository.insert(it.results) }
            users.postValue(usersResponse(response))
      //  } else {
      //      users.postValue(Resource.Error(message = "Error"))
       // }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    private suspend fun usersResponse(response: List<User>): Resource<UserResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
              //  userRepository.insert(resultResponse.results)
                usersPage++
                if (userResponse == null) {
                  //  deletedbUsers()
                    userResponse = resultResponse
                } else {
                    val oldUsers = userResponse?.results
                    val newUsers = resultResponse.results
                    oldUsers?.addAll(newUsers)
                }

               // saveUsers(userResponse!!.results)
                return Resource.Success(userResponse ?: resultResponse)
            }
        }
       return Resource.Error(response.message())
    }

    fun saveUsers(users: List<Result>) = viewModelScope.launch {
      //  userRepository.insert(users)
    }

    suspend fun getSavedUser() = userRepository.getSavedUsers()

    fun deletedbUsers() = viewModelScope.launch {
        userRepository.deleteDbUsers()
    }

    fun refreshUsers()
    {
        userResponse = null
        getUsers()
    }

}
