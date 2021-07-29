package com.andrey.appviewusers.ui.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andrey.appviewusers.R
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.ui.viewModels.MainViewModel
import com.andrey.appviewusers.utils.createViewModel
import com.squareup.picasso.Picasso

class InfoUser : AppCompatActivity() {

    private lateinit var photoImage: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var streetTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var countryTextView: TextView

    private val viewModel: MainViewModel by lazy {
        createViewModel {
            MainViewModel()
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

        viewModel.randomuserResults.value?.get(intent.getIntExtra(GET_NAME_ID, DEFAULT_NUMBER_ID))
            ?.let { displayInfo(it) }

    }

    private fun displayInfo(user: Result) {
        Picasso.with(baseContext)
            .load(user.picture.large)
            .resize(72, 72)
            .centerCrop()
            .into(photoImage)
        nameTextView.text = user.name.getFullName()
        genderTextView.text = user.gender
        ageTextView.text = user.dob.age.toString()
        emailTextView.text = user.email
        streetTextView.text = user.location.street.getFullStreet()
        cityTextView.text = user.location.city
        countryTextView.text = user.location.country
    }

    companion object{
        private const val DEFAULT_NUMBER_ID = 0
        private const val GET_NAME_ID = "id"
    }
}
