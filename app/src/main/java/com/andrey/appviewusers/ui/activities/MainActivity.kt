package com.andrey.appviewusers.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrey.appviewusers.R
import com.andrey.appviewusers.ui.fragments.ViewUsersFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragments, ViewUsersFragment.newInstance())
            .commit()
    }
}
