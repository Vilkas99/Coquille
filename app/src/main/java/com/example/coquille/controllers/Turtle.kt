package com.example.coquille.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coquille.R
import com.example.coquille.models.games.Turtle.TurtleGame

class Turtle : AppCompatActivity() {

    lateinit var gameState : TurtleGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turtle)



    }
}