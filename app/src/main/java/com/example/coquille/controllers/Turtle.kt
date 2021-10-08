package com.example.coquille.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import com.example.coquille.databinding.ActivityTurtleBinding
import com.example.coquille.models.games.Turtle.Position
import com.example.coquille.models.games.Turtle.TurtleGame

class Turtle : AppCompatActivity() {

    lateinit var gameState: TurtleGame
    lateinit var binding: ActivityTurtleBinding

    //Positions
    lateinit var positions: MutableList<Position>
    lateinit var currentPosView: View

    //Abilities
    lateinit var freezeMonkeyView : View
    lateinit var waterView : View
    lateinit var slowMotion : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding
        binding = ActivityTurtleBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //Current Pos
        currentPosView = binding.circuloOrigin
        var currentPos = Position(currentPosView, "player")
        positions.add(currentPos)

        //Get all elements from XML
        createElements(binding.mainLayout)

        //Get FireElements
        var firePositions = mutableListOf(Position(binding.circulo7, "fire"), Position(binding.circulo9, "fire"),Position(binding.circulo1, "fire") )

        //Get monkeyPositions
        var monkeyPositions = mutableListOf(Position(binding.circulo2, "monkey"), Position(binding.circulo8, "monkey"))

        gameState = TurtleGame(currentPos, positions, 0, 120, firePositions, monkeyPositions)

    }

    //TODO: Vincular los objetos con la interacción del usuario

    fun createElements(myLayout: ConstraintLayout) {
        myLayout.forEach { view ->
            val tag = view.getTag()
            if (tag == "position" && view != currentPosView)
                positions.add(Position(view, "normal"))

            else if (tag == "freezeMonkey") freezeMonkeyView = view
            else if (tag == "water") waterView = view
            else if (tag == "slowMotion") slowMotion = view
        }
    }
}