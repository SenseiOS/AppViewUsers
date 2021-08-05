package com.andrey.appviewusers.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrey.appviewusers.R
import com.andrey.appviewusers.ui.viewModels.MainViewModel
import com.andrey.appviewusers.utils.DiUtil
import com.andrey.appviewusers.utils.createViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.andrey.appviewusers.ui.fragments.ViewUsersFragment
import kotlinx.android.synthetic.main.activity_main.*


private const val PUT_ID_NAME = "id"

class MainActivity : AppCompatActivity() {

   // lateinit var adapter: UsersAdapter

    val viewModel: MainViewModel by lazy {
        createViewModel {
            MainViewModel(DiUtil.userRepository)
        }
    }

    //private lateinit var paginationProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragments, ViewUsersFragment.newInstance())
            .commit()
    }
}
