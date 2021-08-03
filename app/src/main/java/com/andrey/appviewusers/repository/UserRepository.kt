package com.andrey.appviewusers.repository

import android.content.Context
import android.graphics.ColorSpace.Model
import android.os.AsyncTask
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


object UserRepository{

    var appDatabase: AppDatabase? = null
    private val mService : RetrofitService by lazy {
        Common.retrofitService
    }

    fun initialDb(context: Context){
        appDatabase = AppDatabase.invoke(context)
    }
    suspend fun getUsers(pageNumber: Int) =
        mService.getSomeData(pageNumber)

    fun insert(users: List<Result>) = appDatabase?.userDao()?.insert(users) //suspend

    fun getSavedUsers() = appDatabase?.userDao()?.getAll()

    fun deleteDbUsers() = appDatabase?.userDao()?.deleteResults()

    fun getUser(id:String) = appDatabase?.userDao()?.getUser(id)

  //  val allUser: List<Result> = userDao.getAll()

  //  suspend fun insert(users:List<Result>){
   //     userDao.insert(users)
  //  }

        //var userDatabase:AppDatabase? = null

 /*       var randomuserResults:MutableLiveData<List<Result>> = MutableLiveData<List<Result>>()

      val mService : RetrofitService by lazy {
          Common.retrofitService
      }

    fun initializeDB(context: Context) {
            userDatabase= AppDatabase.getDatabase(context)
        }

        fun insertData(context: Context, users: List<Result>) {
           // userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.userDao().insert(users)
            }
        }

        fun getUsers(): MutableLiveData<List<Result>> {

           // userDatabase = initializeDB(context)

            randomuserResults.value = userDatabase!!.userDao().getAll()
            return randomuserResults
        }

      fun getUser(id: String): Result? {
          return randomuserResults.value?.find { it.login.uuid == id }
      }

       fun getUserList(context: Context) {

          mService.getSomeData(1).enqueue(object : Callback<UserResponse> {
              override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                  randomuserResults = getUsers(context)
                  Log.d("Er","Error")
              }

              override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                  // adapter.submitLisresponse.body()?.results)
                  randomuserResults.value =response.body()?.results

                  randomuserResults.value?.let { insertData(context, it) }
              }

          })
      }


    //var userDao: UserDao? = null
   // var randomuserResults: MutableLiveData<List<Result>> =  MutableLiveData<List<Result>>()
    //var appDatabase: AppDatabase? = null
*/

    }
