package com.roman.androidscrumboard.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.roman.androidscrumboard.R
import com.roman.androidscrumboard.models.UserLocalStore
import com.roman.androidscrumboard.ui.logIn.LogInActivity

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        //переделать mvvm
        val logOutButton: Button = root.findViewById(R.id.setting_log_out_button)
        logOutButton.setOnClickListener{
            val userLocalStore = UserLocalStore(context!!)
            userLocalStore.clearUserData()
            val intent = Intent(activity, LogInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return root
    }
}