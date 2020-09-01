package com.roman.androidscrumboard.ui.newProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewProjectViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is new project Fragment"
    }
    val text: LiveData<String> = _text
}