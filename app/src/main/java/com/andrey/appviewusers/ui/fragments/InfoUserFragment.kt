package com.andrey.appviewusers.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.andrey.appviewusers.R
import com.andrey.appviewusers.databinding.FragmentViewUsersBinding
import com.andrey.appviewusers.db.User
import com.andrey.appviewusers.ui.activities.MainActivity
import com.andrey.appviewusers.ui.viewModels.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.andrey.appviewusers.databinding.FragmentInfoUserBinding


class InfoUserFragment : Fragment(R.layout.fragment_info_user) {

    lateinit var viewModel: MainViewModel

    private var viewBinding: FragmentInfoUserBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewBinding = FragmentInfoUserBinding.bind(view)
       // viewModel.users.observe(viewLifecycleOwner, Observer { displayInfo(it) })
        //intent.getStringExtra(GET_NAME_ID)?.let { viewModel.getUser(it) }
       /* val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }*/

        arguments?.getString(GET_NAME_ID)?.let { Log.d("Message", it) }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun displayInfo(user: User) {
        //viewBinding!!.toolBar.textAlignment = user.username
        activity?.let {
            Glide.with(it.baseContext)
                .load(user.large)
                .transform(CircleCrop())
                .into(viewBinding!!.imvPhoto)
        }
        viewBinding!!.txtName.text = user.getFullName()
        viewBinding!!.txtGender.text = user.gender
        viewBinding!!.txtAge.text = user.age.toString()
        viewBinding!!.txtAge.text = user.email
        viewBinding!!.txtStreet.text = user.getFullStreet()
        viewBinding!!.txtCity.text = user.city
        viewBinding!!.txtCountry.text = user.country
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
