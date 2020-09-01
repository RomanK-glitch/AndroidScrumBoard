package com.roman.androidscrumboard.extentions

import androidx.lifecycle.MutableLiveData

// Set default value for any type of MutableLiveData
fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
// Set new value for any type of MutableLiveData
fun <T : Any?> MutableLiveData<T>.set(newValue: T) = apply { setValue(newValue) }
