package com.roman.androidscrumboard.ui.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.roman.androidscrumboard.R
import com.roman.androidscrumboard.models.UserLocalStore

class ProjectsFragment : Fragment() {

    private lateinit var projectsViewModel: ProjectsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        projectsViewModel =
                ViewModelProviders.of(this).get(ProjectsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_projects, container, false)

        val searchButton: Button = root.findViewById(R.id.projects_search_button)
        searchButton.setOnClickListener{
            val userLocalStore = UserLocalStore(context!!)
            Toast.makeText(activity, userLocalStore.getLoggedInUser().id.toString(), Toast.LENGTH_LONG).show()
            Toast.makeText(activity, userLocalStore.getLoggedInUser().userName, Toast.LENGTH_LONG).show()
            Toast.makeText(activity, userLocalStore.getLoggedInUser().eMail, Toast.LENGTH_LONG).show()
            Toast.makeText(activity, userLocalStore.getLoggedInUser().password, Toast.LENGTH_LONG).show()
        }

        return root
    }
}