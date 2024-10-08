package com.example.lab1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
//import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    //private var viewModel by viewModels<TextViewModel>()
    private lateinit var viewModel: TextViewModel
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[TextViewModel::class.java]
        textView = findViewById(R.id.textView)
        findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.updateText()
            textView.text = viewModel.text
        }
    }

    ///override fun onResume() {
    ///    super.onResume()
    ///    textView.text = viewModel.text
    ///}
}