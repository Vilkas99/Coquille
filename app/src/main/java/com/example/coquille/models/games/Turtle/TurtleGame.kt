package com.example.coquille.models.games.Turtle

import android.content.Context
import android.view.View
import com.example.coquille.R
import com.example.coquille.models.abstracts.Game
import com.example.coquille.utils.Utils

class TurtleGame(currentPosition : View, positions : MutableList<View>, points :Int, totalTime: Int, firePositions: MutableList<View>,  monkeyPositions: MutableList<View> ): Game("Tortuguita", "¡El pantano se está incendiando! Apaga todos " + "los fuegos y evita a los changuitos antes de que se acabe el tiempo.") {

    var currentPosition = currentPosition
    var positions = positions
    var firePositions = firePositions
    var monkeyPositions = monkeyPositions

    var currentState = ""
    var points = points
    var totalTime = totalTime


    fun handleInteraction(context: Context, payload : Any?){
        if(currentState == "move") {
            nextMovement(payload as View, context)
        } else if(currentState == "removeFire") removeFire(payload as View)
    }

    fun nextMovement(nextPos : View, context : Context)  {
        if (isANeighbour(nextPos)){
            if (!firePositions.contains(nextPos) && !monkeyPositions.contains(nextPos)){

                var posData = currentPosition.getTag() as Position

                //Accedemos a la posición actual
                currentPosition.setBackgroundResource(R.drawable.circle)
                posData.state = "normal"
                currentPosition.setTag(posData)

                //Establecemos la posición actual como la siguiente posición
                currentPosition = nextPos
                var newData = currentPosition.getTag() as Position
                currentPosition.setBackgroundResource(R.drawable.player_circle)
                newData.state = "player"
                currentPosition.setTag(newData)

                return
            }
            Utils.sendMessage("¡Esa posición está ocupada!", context)
            return
        }
        Utils.sendMessage("Estás demasiado lejos :(", context)
        return
    }

    fun removeFire(firePos : View){
        if (isANeighbour(firePos)){
            firePositions.remove(firePos)

            val fireData = firePos.getTag() as Position
            firePos.setBackgroundResource(R.drawable.player_circle)
            fireData.state = "normal"
            firePos.setTag(fireData)
        }
    }


    fun setMonkeyPositions(){
        var newMonkeys = mutableListOf<View>()
        monkeyPositions.forEach{
            var newPossiblePosition: View
            do {
                val nextIndexRandom = (0 until positions.size).random()
                newPossiblePosition = positions[nextIndexRandom]
            } while ( newPossiblePosition in firePositions || newPossiblePosition in monkeyPositions)

            val newMonkeyData = newPossiblePosition.getTag() as Position
            newMonkeyData.state = "monkey"

            newMonkeys.add(newPossiblePosition)
            newPossiblePosition.setBackgroundResource(R.drawable.monkey_circle)
        }

        monkeyPositions = newMonkeys
    }


    fun isANeighbour(positionToEvaluate : View) : Boolean{
        var posToEvaluateData = positionToEvaluate.getTag() as Position
        var currentPosData = currentPosition.getTag() as Position

        var nextCol = posToEvaluateData.col
        var nextRow = posToEvaluateData.row

        var myCol = currentPosData.col
        var myRow = currentPosData.row

        val sameCol = myCol == nextCol
        val sameRow = myRow == nextRow

        if(sameCol || sameRow){
            val sameX = (myCol + 1 == nextCol) || (myCol -1 == nextCol)
            val sameY = (myRow + 1 == nextRow) || (myRow -1 == nextRow)

            if (sameX || sameY) return true
        }

        return false
    }
}
