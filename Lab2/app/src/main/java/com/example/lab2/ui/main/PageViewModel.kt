package com.example.lab2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = _index.map {
        "It is tab $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}