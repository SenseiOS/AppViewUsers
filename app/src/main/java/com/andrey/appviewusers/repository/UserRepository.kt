package com.andrey.appviewusers.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.db.UserDao
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.model.UserResponse
import com.andrey.appviewusers.retrofit.Common
import com.andrey.appviewusers.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository (
    private val userDao: UserDao
        ){

  //  val allUser: List<Result> = userDao.getAll()

  //  suspend fun insert(users:List<Result>){
   //     userDao.insert(users)
  //  }
    companion object {

        var userDatabase:AppDatabase? = null

        var randomuserResults:MutableLiveData<List<Result>> = MutableLiveData<List<Result>>()

      val mService : RetrofitService by lazy {
          Common.retrofitService
      }

        private fun initializeDB(context: Context) : AppDatabase? {
            return AppDatabase.getDatabase(context)
        }

        fun insertData(context: Context, users: MutableLiveData<List<Result>>) {
            userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.userDao().insert(users)
            }
        }

        fun getUsers(context: Context): MutableLiveData<List<Result>> {

            userDatabase = initializeDB(context)

            randomuserResults.value = userDatabase!!.userDao().getAll()
            return randomuserResults
        }

      fun getUser(id: String): Result? {
          return randomuserResults.value?.find { it.login.uuid == id }
      }

       fun getUserList(context: Context) {

          mService.getSomeData().enqueue(object : Callback<UserResponse> {
              override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                //  randomuserResults = getUsers(context)
                  Log.d("Er","Error")
              }

              override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                  // adapter.submitLisresponse.body()?.results)
                  randomuserResults.postValue(response.body()?.results)

                 // insertData(context, randomuserResults)
              }

          })
      }
    }
}
