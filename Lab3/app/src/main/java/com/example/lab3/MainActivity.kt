package com.example.lab3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab3.adapters.UniversalAdapter
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.models.Character
import com.example.lab3.models.Film

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
            Film(
                title = "A New Hope",
                date = "1977-05-25",
                opening = "It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil Galactic Empire.\nDuring the battle, Rebel spies managed to steal secret plans to the Empire's ultimate weapon, the DEATH STAR, an armored space station with enough power to destroy an entire planet.\nPursued by the Empire's sinister agents, Princess Leia races home aboard her starship, custodian of the stolen plans that can save her people and restore freedom to the galaxy...."
            ),
            Character("Leia Organa", 150, 49, "19BBY"),
            Character("Owen Lars", 178, 120, "52BBY"),
            Film(
                title = "The Empire Strikes Back",
                date = "1980-05-17",
                opening = "It is a dark time for the Rebellion. Although the Death Star has been destroyed, Imperial troops have driven the Rebel forces from their hidden base and pursued them across the galaxy.\nEvading the dreaded Imperial Starfleet, a group of freedom fighters led by Luke Skywalker has established a new secret base on the remote ice world of Hoth.\nThe evil lord Darth Vader, obsessed with finding young Skywalker, has dispatched thousands of remote probes into the far reaches of space...."
            ),
            Character("Beru Whitesun lars", 165, 75, "47BBY"),
            Character("R5-D4", 97, 32, "unknown"),
            Character("Biggs Darklighter", 183, 84, "24BBY"),
            Film(
                title = "Return of the Jedi",
                date = "1983-05-25",
                opening = "Luke Skywalker has returned to his home planet of Tatooine in an attempt to rescue his friend Han Solo from the clutches of the vile gangster Jabba the Hutt.\nLittle does Luke know that the GALACTIC EMPIRE has secretly begun construction on a new armored space station even more powerful than the first dreaded Death Star.\nWhen completed, this ultimate weapon will spell certain doom for the small band of rebels struggling to restore freedom to the galaxy..."
            ),
            Character("Obi-Wan Kenobi", 182, 77, "57BBY"),
            Character("Anakin Skywalker", 188, 84, "41.9BBY"),
            Character("Wilhuff Tarkin", 180, null, "64BBY"),
            Character("Chewbacca", 228, 112, "200BBY"),
            Film(
                title = "The Phantom Menace",
                date = "1999-05-19",
                opening = "Turmoil has engulfed the Galactic Republic. The taxation of trade routes to outlying star systems is in dispute.\nHoping to resolve the matter with a blockade of deadly battleships, the greedy Trade Federation has stopped all shipping to the small planet of Naboo.\nWhile the Congress of the Republic endlessly debates this alarming chain of events, the Supreme Chancellor has secretly dispatched two Jedi Knights, the guardians of peace and justice in the galaxy, to settle the conflict...."
            ),
            Film(
                title = "Attack of the Clones",
                date = "2002-05-16",
                opening = "There is unrest in the Galactic Senate. Several thousand solar systems have declared their intentions to leave the Republic.\nThis separatist movement, under the leadership of the mysterious Count Dooku, has made it difficult for the limited number of Jedi Knights to maintain peace and order in the galaxy.\nSenator Amidala, the former Queen of Naboo, is returning to the Galactic Senate to vote on the critical issue of creating an ARMY OF THE REPUBLIC to assist the overwhelmed Jedi...."
            ),
            Character("Han Solo", 180, 80, "29BBY"),
            Character("Greedo", 173, 74, "44BBY"),
            Character("Jabba Desilijic Tiure", 175, 1358, "600BBY"),
            Character("Wedge Antilles", 170, 77, "21BBY"),
            Character("Jek Tono Porkins", 180, 110, "unknown"),
            Film(
                title = "Revenge of the Sith",
                date = "2005-05-19",
                opening = "War! The Republic is crumbling under attacks by the ruthless Sith Lord, Count Dooku. There are heroes on both sides. Evil is everywhere.\nIn a stunning move, the fiendish droid leader, General Grievous, has swept into the Republic capital and kidnapped Chancellor Palpatine, leader of the Galactic Senate.\nAs the Separatist Droid Army attempts to flee the besieged capital with their valuable hostage, two Jedi Knights lead a desperate mission to rescue the captive Chancellor...."
            ),
            Character("Yoda", 66, 17, "896BBY"),
            Character("Palpatine", 170, 75, "82BBY")
        )
        binding.rv.adapter = UniversalAdapter(characters)
    }
}