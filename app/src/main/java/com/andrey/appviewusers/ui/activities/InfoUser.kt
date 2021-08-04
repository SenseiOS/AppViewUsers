package com.andrey.appviewusers.ui.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.andrey.appviewusers.R
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.ui.viewModels.InfoUserViewModel
import com.andrey.appviewusers.utils.DiUtil
import com.andrey.appviewusers.utils.createViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class InfoUser : AppCompatActivity() {

    private lateinit var photoImage: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var streetTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var countryTextView: TextView

    private val viewModel: InfoUserViewModel by lazy {
        createViewModel {
            InfoUserViewModel(DiUtil.userRepository)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_user)

        photoImage = findViewById(R.id.imv_photo)
        nameTextView = findViewById(R.id.txt_name)
        genderTextView = findViewById(R.id.txt_gender)
        ageTextView = findViewById(R.id.txt_age)
        emailTextView = findViewById(R.id.txt_email)
        streetTextView = findViewById(R.id.txt_street)
        cityTextView = findViewById(R.id.txt_city)
        countryTextView = findViewById(R.id.txt_country)

        viewModel.user.observe(this, Observer { displayInfo(it) })
        //intent.getStringExtra(GET_NAME_ID)?.let { viewModel.getUser(it) }

    }

    private fun displayInfo(user: Result) {
        title = user.login.username
        Glide.with(baseContext)
            .load(user.picture.large)
            .transform(CircleCrop())
            .into(photoImage)
        nameTextView.text = user.name.getFullName()
        genderTextView.text = user.gender
        ageTextView.text = user.dob.age.toString()
        emailTextView.text = user.email
        streetTextView.text = user.location.street.getFullStreet()
        cityTextView.text = user.location.city
        countryTextView.text = user.location.country
    }

    companion object {
        private const val GET_NAME_ID = "id"
    }
}
