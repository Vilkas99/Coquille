package com.example.coquille.models.games.Turtle

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.example.coquille.R
import com.example.coquille.controllers.activity_home
import com.example.coquille.models.abstracts.Game
import com.example.coquille.utils.Utils

class TurtleGame(currentPosition : View, points :Int, totalTime: Int, firePositions: MutableList<View>,  monkeyPositions: MutableList<View> ): Game("Tortuguita", "¡El pantano se está incendiando! Apaga todos " + "los fuegos y evita a los changuitos antes de que se acabe el tiempo.") {

    var currentPosition = currentPosition
    var firePositions = firePositions
    var monkeyPositions = monkeyPositions

    var currentState = ""
    var playerState = ""

    var points = points
    var totalTime = totalTime

    var coolDownWater = 0
    var coolDownSlowMotion = 0
    var coolDownFreezeMonkey = 0

    var slowDuration = 0
    var freezeDuration = 0


    fun evaluateGameState(context: Context, gameEnded : Boolean){
        if (firePositions.size == 0){
            Utils.sendMessage("¡Has ganado!",context )
            currentState = "won"
            points += 50
        }

        else if(gameEnded){
            currentState = "finished"
            Utils.sendMessage("¡Se ha terminado el tiempo!",context )
        }
    }

    //TODO: Desarrollar el resto de habilidades

    fun removeCoolDown(){
        if (coolDownWater > 0) coolDownWater -= 1

        if (coolDownSlowMotion > 0){
            coolDownSlowMotion -= 1
            if (slowDuration > 0) slowDuration -= 1
        }
        else if (coolDownSlowMotion == 0 && currentState == "slow") currentState = "normal"

        if (coolDownFreezeMonkey > 0){
            coolDownFreezeMonkey -= 1
            if(freezeDuration > 0) freezeDuration -= 1
        }
        else if (coolDownFreezeMonkey == 0 && currentState == "freeze") currentState = "normal"
    }




    fun handleInteraction(nextState : String , context: Context, payload : Any?) : Boolean{
        if((nextState == "move" && (playerState == "moving" || currentState == "slow"))) {
            if (nextMovement(payload as View, context)){
                playerState = "normal"
                slowDuration = 0
                return true
            }
            return false

        } else if(nextState == "removeFire"){
            if (coolDownWater == 0 && removeFire(payload as View, context)){
                coolDownWater = 3
                playerState = "normal"
                return true
            } else {
                return false
            }
        } else if(nextState == "slowMotion"){
            if(coolDownSlowMotion == 0){
                currentState = "slow"
                coolDownSlowMotion = 15
                slowDuration = 6
                return true
            }
        } else if (nextState == "freezeMonkey"){
            if (coolDownFreezeMonkey == 0){
                currentState = "freeze"
                coolDownFreezeMonkey = 10
                freezeDuration = 4
                return true
            }
        }
        return false
    }

    fun nextMovement(nextPos : View, context : Context)  : Boolean {
        if (isANeighbour(nextPos) || currentState == "slow"){
            if (!firePositions.contains(nextPos) && !monkeyPositions.contains(nextPos)){

                var posData = currentPosition.getTag() as Position

                //Accedemos a la posición actual
                posData.state = "position"
                currentPosition.setTag(posData)

                //Establecemos la posición actual como la siguiente posición
                currentPosition = nextPos
                var newData = currentPosition.getTag() as Position
                newData.state = "player"
                currentPosition.setTag(newData)

                return true
            }
            Utils.sendMessage("¡Esa posición está ocupada!", context)
            return false
        }
        Utils.sendMessage("Estás demasiado lejos :(", context)
        return false
    }

    fun removeFire(firePos : View, context: Context) : Boolean{
        if ((isANeighbour(firePos) || currentState == "slow") && firePositions.contains(firePos)){
            firePositions.remove(firePos)

            val fireData = firePos.getTag() as Position
            fireData.state = "position"
            firePos.setTag(fireData)

            val nuevosPuntos = 200 - totalTime
            points += nuevosPuntos

            coolDownWater += 3
            Utils.sendMessage("¡Recibiste :" + nuevosPuntos.toString() + " puntos!", context)
            return true
        }

        return false
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

    fun returnNeighbour(positionsToEvaluate: MutableList<View> ) : MutableList<View>{
        var mutableList = mutableListOf<View>()
        positionsToEvaluate.forEach{ position ->
            if(isANeighbour(position))
                mutableList.add(position)
        }

        return mutableList
    }
}
