package com.andrey.appviewusers.ui.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.repository.UserRepository

class InfoUserViewModel(
    context: Context
):ViewModel() {

    val user: MutableLiveData<Result> = MutableLiveData<Result>()

    //val userRepository: UserRepository = UserRepository(AppDatabase.invoke(context))

    fun getUser(id: String) {
        UserRepository.getUser(id)?.let {
            user?.value = it
        }
    }
}
