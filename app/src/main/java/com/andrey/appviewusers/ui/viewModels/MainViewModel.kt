package com.andrey.appviewusers.ui.viewModels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.model.UserResponse
import com.andrey.appviewusers.repository.UserRepository
import com.andrey.appviewusers.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
   val  context: Context
) : ViewModel() {


    val users: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    var usersPage = 1
    var userResponse: UserResponse? = null


    init {
        UserRepository.initialDb(context)
        getUsers()
    }

    fun getUsers() = viewModelScope.launch {
        users.postValue(Resource.Loading())
        if (isOnline(context)) {
            val response = UserRepository.getUsers(usersPage)
            response.body()?.let { UserRepository.insert(it.results) }
            users.postValue(handleBreakingNewsResponse(response))
        } else {
            users.postValue(Resource.Error(message = "Error"))
        }
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

    private fun handleBreakingNewsResponse(response: Response<UserResponse>): Resource<UserResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                UserRepository.insert(resultResponse.results)
                usersPage++
                if (userResponse == null) {
                    deletedbUsers()
                    userResponse = resultResponse
                } else {
                    val oldUsers = userResponse?.results
                    val newUsers = resultResponse.results
                    oldUsers?.addAll(newUsers)
                }

                saveUsers(userResponse!!.results)
                return Resource.Success(userResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveUsers(users: List<Result>) = viewModelScope.launch {
        UserRepository.insert(users)
    }

    fun getSavedUser() = UserRepository.getSavedUsers()

    fun deletedbUsers() = viewModelScope.launch {
        UserRepository.deleteDbUsers()
    }

    fun refreshUsers()
    {
        userResponse = null
        getUsers()
    }

}
