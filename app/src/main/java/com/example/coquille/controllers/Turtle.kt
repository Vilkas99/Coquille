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
import com.example.coquille.utils.Utils

class Turtle : AppCompatActivity() {

    lateinit var gameState: TurtleGame
    lateinit var binding: ActivityTurtleBinding

    //Positions
    lateinit var positions: MutableList<View>
    lateinit var currentPosView: View

    //Obstacles
    lateinit var firePositions: MutableList<View>
    lateinit var monkeyPositions: MutableList<View>

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
        positions = mutableListOf<View>()
        currentPosView = binding.circuloOrigin
        var currentPos = Position(Utils.getId(currentPosView), "player", 0, 1)
        currentPosView.setTag(currentPos)

        positions.add(currentPosView)

        //Get all elements from XML
        createElements(binding.mainLayout)

        //Get FireElements

        val fire1 = Position(Utils.getId(binding.circulo7), "fire", 3, 1 )
        binding.circulo7.setTag(fire1)

        val fire2 = Position(Utils.getId(binding.circulo9), "fire", 2, 0)
        binding.circulo9.setTag(fire2)

        val fire3 = Position(Utils.getId(binding.circulo1), "fire", 0, 0)
        binding.circulo1.setTag(fire3)

        firePositions = mutableListOf(binding.circulo1, binding.circulo9, binding.circulo7)

        //Get monkeyPositions
        val monkey1 = Position(Utils.getId(binding.circulo8), "monkey", 3, 0)
        binding.circulo8.setTag(monkey1)

        val moneky2 = Position(Utils.getId(binding.circulo2), "monkey", 0, 2)
        binding.circulo2.setTag(moneky2)

        monkeyPositions = mutableListOf(binding.circulo8, binding.circulo2)

        gameState = TurtleGame(currentPosView, positions, 0, 120, firePositions, monkeyPositions)

    }

    //TODO: Vincular los objetos con la interacción del usuario


    fun handleInteraction(view: View?){
        val tag = view?.getTag()
        if( tag == "position" || tag == "fire"){
            gameState.handleInteraction(this, view)
        } else if (tag == "slowMotion"){
            gameState.currentState = "slowMotion"
            gameState.handleInteraction(this, null)
        } else if (tag == "freezeMonkey"){
            gameState.currentState = "freezeMonkey"
            gameState.handleInteraction(this, null)
        } else if (tag == "player"){
            gameState.currentState = "move"
        } else if(tag == "water"){
            gameState.currentState = "removeFire"
        }
    }

    fun createElements(myLayout: ConstraintLayout) {
        myLayout.forEach { view ->

            view.setOnClickListener { object : View.OnClickListener {
                override fun onClick(view: View?) {
                        handleInteraction(view)
                    }
                }
            }

            if (Utils.getId(view) == "@+id/water") waterView = view
            else if (Utils.getId(view) == "@+id/freeze") freezeMonkeyView = view
            else if (Utils.getId(view) == "@+id/slowMotion") slowMotion = view
            else {
                if (!firePositions.contains(view) && !monkeyPositions.contains(view) && currentPosView != view) {
                    val (row, col) = getRowAndCol(view.getTag() as String)
                    val position = Position(Utils.getId(view), "position", row, col)
                    view.setTag(position)

                    positions.add(view)
                }
            }
        }
    }

    fun getRowAndCol(tag : String) : Pair<Int, Int>{
        val row = tag[0].code
        val col = tag[2].code

        val result = Pair(row, col)

        return result
    }


}