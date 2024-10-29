package com.example.lab3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab3.adapters.CharacterAdapter
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.models.Character

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val characters = listOf(
            Character("Luke Skywalker", 172, 77, "19BBY"),
            Character("C-3PO", 167, 75, "112BBY"),
            Character("R2-D2", 96, 32, "33BBY"),
            Character("Darth Vader", 202, 136, "41.9BBY"),
            Character("Leia Organa", 150, 49, "19BBY"),
            Character("Owen Lars", 178, 120, "52BBY"),
            Character("Beru Whitesun lars", 165, 75, "47BBY"),
            Character("R5-D4", 97, 32, "unknown"),
            Character("Biggs Darklighter", 183, 84, "24BBY"),
            Character("Obi-Wan Kenobi", 182, 77, "57BBY"),
            Character("Anakin Skywalker", 188, 84, "41.9BBY"),
            Character("Wilhuff Tarkin", 180, null, "64BBY"),
            Character("Chewbacca", 228, 112, "200BBY"),
            Character("Han Solo", 180, 80, "29BBY"),
            Character("Greedo", 173, 74, "44BBY"),
            Character("Jabba Desilijic Tiure", 175, 1358, "600BBY"),
            Character("Wedge Antilles", 170, 77, "21BBY"),
            Character("Jek Tono Porkins", 180, 110, "unknown"),
            Character("Yoda", 66, 17, "896BBY"),
            Character("Palpatine", 170, 75, "82BBY")
        )
        binding.rv.adapter = CharacterAdapter(characters)
    }
}