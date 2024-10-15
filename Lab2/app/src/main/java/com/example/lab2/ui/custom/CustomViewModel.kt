package com.example.lab2.ui.custom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is My Custom Fragment"
    }
    val text: LiveData<String> = _text
}