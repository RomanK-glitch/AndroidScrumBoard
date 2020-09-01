package com.roman.androidscrumboard.ui.editProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.roman.androidscrumboard.R

class EditProfileFragment : Fragment() {

    private lateinit var editProfileViewModel: EditProfileViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        editProfileViewModel =
                ViewModelProviders.of(this).get(EditProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        val textView: TextView = root.findViewById(R.id.text_edit_profile)
        editProfileViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}