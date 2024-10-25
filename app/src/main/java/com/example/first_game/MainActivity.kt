package com.example.first_game

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var rb: RadioButton
    lateinit var rg: RadioGroup
    lateinit var tmp: TextView  // Riferimento alla TextView "tmp"
    lateinit var result: TextView  // Riferimento alla TextView "result"
    lateinit var play: Button

    var appValue = 0
    var userValue = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play = findViewById(R.id.play)
        rg = findViewById(R.id.radioG)
        tmp = findViewById(R.id.tmp) // Assicurati che esista un TextView con ID tmp in activity_main.xml
        result = findViewById(R.id.result) // Assicurati che esista un TextView con ID result in activity_main.xml

        rg.setOnCheckedChangeListener{ _: RadioGroup, id: Int -> printSelected(id) }

        play.setOnClickListener {
            play()
        }

    }

    private fun random(): Int {
        val value = Random.nextInt(3)
        tmp.text = when (value) {
            0 -> getString(R.string.rock)
            1 -> getString(R.string.paper)
            else -> getString(R.string.scissor)
        }
        return value
    }

    private fun result(id: Int) {
        userValue = when (id) {
            R.id.rock -> 0
            R.id.paper -> 1
            else -> 2
        }
    }

    private fun play() {
        appValue = random()
        when (rg.checkedRadioButtonId) {
            R.id.rock -> userValue = 0
            R.id.paper -> userValue = 1
            R.id.scissor -> userValue = 2
            else -> userValue = -1  // Caso in cui nessuna opzione è selezionata
        }


        if (userValue == -1) {
            tmp.text = getString(R.string.insert)
        } else if (appValue == userValue) {
            result.text = getString(R.string.even)
        } else {
            result.text = when (userValue) {
                0 -> if (appValue == 2) getString(R.string.user) else getString(R.string.app)
                1 -> if (appValue == 0) getString(R.string.user) else getString(R.string.app)
                else -> if (appValue == 1) getString(R.string.user) else getString(R.string.app)
            }
        }
    }

    private fun printSelected(id: Int) {
        rb = findViewById(id)
        Toast.makeText(applicationContext, rb.text, Toast.LENGTH_SHORT).show()
    }
}
