package com.roman.androidscrumboard.ui.newProject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.roman.androidscrumboard.R

class NewProjectFragment : Fragment() {

    private lateinit var newProjectViewModel: NewProjectViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newProjectViewModel =
                ViewModelProviders.of(this).get(NewProjectViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_project, container, false)
        val textView: TextView = root.findViewById(R.id.text_new_project)
        newProjectViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}