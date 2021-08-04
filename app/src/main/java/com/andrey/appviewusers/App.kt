package com.andrey.appviewusers

import android.app.Application
import com.andrey.appviewusers.utils.DiUtil

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DiUtil.init(this)
    }

}
