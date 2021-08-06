package com.andrey.appviewusers.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andrey.appviewusers.base.BaseFragment

inline fun <reified VM : ViewModel> BaseFragment<*>.createViewModel(
    noinline viewModelInitializer: () -> VM
): VM {
    return ViewModelProvider(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModelInitializer() as T
        }

    })[VM::class.java]
}
