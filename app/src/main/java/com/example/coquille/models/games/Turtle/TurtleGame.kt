package com.example.coquille.models.games.Turtle

import android.content.Context
import android.view.View
import com.example.coquille.R
import com.example.coquille.models.abstracts.Game
import com.example.coquille.utils.Utils

class TurtleGame(currentPosition : Position, positions : MutableList<Position>, points :Int, totalTime: Int, firePositions: MutableList<Position>,  monkeyPositions: MutableList<Position> ): Game("Tortuguita", "¡El pantano se está incendiando! Apaga todos " + "los fuegos y evita a los changuitos antes de que se acabe el tiempo.") {

    var currentPosition = currentPosition
    var positions = positions
    var firePositions = firePositions
    var monkeyPositions = monkeyPositions

    var currentState = ""
    var points = points
    var totalTime = totalTime


    fun handleInteraction(context: Context, payload : Any?){
        if(currentState == "move") {
            nextMovement(payload as Position, context)
        } else if(currentState == "removeFire") removeFire(payload as Position)
    }

    fun nextMovement(nextPos : Position, context : Context)  {
        if (isANeighbour(nextPos)){
            if (!firePositions.contains(nextPos) && !monkeyPositions.contains(nextPos)){
                currentPosition.viewReference.setBackgroundResource(R.drawable.circle)
                currentPosition.state = "normal"

                currentPosition = nextPos
                currentPosition.viewReference.setBackgroundResource(R.drawable.player_circle)
                currentPosition.state = "player"
                return
            }
            Utils.sendMessage("¡Esa posición está ocupada!", context)
            return
        }
        Utils.sendMessage("Estás demasiado lejos :(", context)
        return
    }

    fun removeFire(firePos : Position){
        if (isANeighbour(firePos)){
            firePositions.remove(firePos)
            firePos.viewReference.setBackgroundResource(R.drawable.player_circle)
            firePos.state = "normal"
        }
    }


    fun setMonkeyPositions(){
        var newMonkeys = mutableListOf<Position>()
        monkeyPositions.forEach{
            var newPossiblePosition: Position
            do {
                val nextIndexRandom = (0 until positions.size).random()
                newPossiblePosition = positions[nextIndexRandom]
            } while ( newPossiblePosition in firePositions || newPossiblePosition in monkeyPositions)

            newMonkeys.add(newPossiblePosition)
            newPossiblePosition.viewReference.setBackgroundResource(R.drawable.monkey_circle)
        }

        monkeyPositions = newMonkeys
    }


    fun isANeighbour(positionToEvaluate : Position) : Boolean{
        currentPosition.neighbours.forEach { neighbour ->
            if (neighbour == positionToEvaluate){
                return true
            }
        }
        return false
    }
}
