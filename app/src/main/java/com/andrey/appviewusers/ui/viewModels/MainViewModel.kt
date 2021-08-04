package com.andrey.appviewusers.ui.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.model.UserResponse
import com.andrey.appviewusers.repository.UserRepository
import com.andrey.appviewusers.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    context: Context
): ViewModel() {

   // val  userRepository: UserRepository = UserRepository(AppDatabase.invoke(context))

    val users: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    var usersPage = 1
    var userResponse: UserResponse? = null


    init {
        UserRepository.initialDb(context)
        getUsers()
    }

    fun getUsers() = viewModelScope.launch {
        users.postValue(Resource.Loading())
        val response = UserRepository.getUsers(usersPage)
        response.body()?.let { UserRepository.insert(it.results) }
        users.postValue(handleBreakingNewsResponse(response))
    }


    private fun handleBreakingNewsResponse(response: Response<UserResponse>) : Resource<UserResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
               // UserRepository.insert(resultResponse.results)
                usersPage++
                if(userResponse == null) {
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

       // users.postValue(Resource.Success((userResponse ?: getSavedUser()) as UserResponse))

        return Resource.Error(response.message())
    }

    fun saveUsers(users:List<Result>) = viewModelScope.launch {
        UserRepository.insert(users)
    }

    fun getSavedUser() = UserRepository.getSavedUsers()

    fun deletedbUsers() = viewModelScope.launch {
        UserRepository.deleteDbUsers()
    }


    /*  lateinit var randomuserResults: MutableLiveData<List<Result>>


      init {
          UserRepository.initializeDB(context)
          getUsers(context)
      }

      fun getUsers(context: Context) {

          UserRepository.getUserList(context)

          randomuserResults = UserRepository.randomuserResults
      }
  */
}
