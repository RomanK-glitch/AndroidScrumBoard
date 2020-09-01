package com.roman.androidscrumboard.ui.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class ProjectsActivityState {
    class LoadingProjectsState(): ProjectsActivityState()
    class ProjectsErrorState(): ProjectsActivityState()
    class ProjectsLoadedState(): ProjectsActivityState()
    class ProjectsNoItemsState(): ProjectsActivityState()
}

class ProjectsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is projects Fragment"
    }
    val text: LiveData<String> = _text
}