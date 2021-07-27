package com.andrey.appviewusers.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> AppCompatActivity.createViewModel(
    noinline viewModelInitializer: () -> VM
): VM {
    return ViewModelProvider(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModelInitializer() as T
        }

    })[VM::class.java]
}
