package com.example.lab1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel : ViewModel() {
    private var _text = MutableLiveData("Hello, Android!")
    val text: LiveData<String> get() = _text

    fun updateText(){
        _text.value = java.util.UUID.randomUUID().toString()
    }
}
