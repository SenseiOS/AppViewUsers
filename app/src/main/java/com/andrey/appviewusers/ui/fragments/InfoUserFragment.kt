package com.andrey.appviewusers.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.andrey.appviewusers.base.BaseFragment
import com.andrey.appviewusers.databinding.FragmentInfoUserBinding
import com.andrey.appviewusers.db.User
import com.andrey.appviewusers.ui.viewModels.InfoUserViewModel
import com.andrey.appviewusers.utils.DiUtil
import com.andrey.appviewusers.utils.createViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class InfoUserFragment : BaseFragment<FragmentInfoUserBinding>() {

    override val viewBindingProvider: (LayoutInflater, ViewGroup?) -> FragmentInfoUserBinding =
        { inflater, container ->
            FragmentInfoUserBinding.inflate(inflater, container, false)
        }
    private val viewModel: InfoUserViewModel by lazy {
        createViewModel {
            InfoUserViewModel(DiUtil.userRepository)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner, Observer { displayInfo(it) })

        arguments?.getString(GET_NAME_ID)?.let { viewModel.getUser(it) }

    }

    private fun displayInfo(user: User) {
        with(binding) {
            activity?.let {
                Glide.with(it.baseContext)
                    .load(user.large)
                    .transform(CircleCrop())
                    .into(imvPhoto)
            }
            txtName.text = user.getFullName()
            txtGender.text = user.gender
            txtAge.text = user.age.toString()
            txtEmail.text = user.email
            txtStreet.text = user.getFullStreet()
            txtCity.text = user.city
            txtCountry.text = user.country
        }
    }


    companion object {

        private const val GET_NAME_ID = "id"

        fun newInstance(itemId: String) = InfoUserFragment().apply {
            arguments = Bundle().apply {
                putString(GET_NAME_ID, itemId)
            }
        }
    }


}
