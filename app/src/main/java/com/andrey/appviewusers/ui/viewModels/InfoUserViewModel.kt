package com.andrey.appviewusers.ui.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.db.User
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.repository.UserRepository

class InfoUserViewModel(
    private val userRepository: UserRepository
):ViewModel() {

    val user: MutableLiveData<User> = MutableLiveData<User>()

    //val userRepository: UserRepository = UserRepository(AppDatabase.invoke(context))

    /*suspend fun getUser(id: String) {
        userRepository.getUser(id)?.let {
            user?.value = it
        }
    }*/
}
