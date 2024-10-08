package com.example.lab1

import androidx.lifecycle.ViewModel

class TextViewModel : ViewModel() {
    private var _text = ""
    val text get() = _text

    fun updateText(){
        _text = java.util.UUID.randomUUID().toString()
    }
}
