package com.example.coquille.models.games.Turtle

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.example.coquille.R
import com.example.coquille.controllers.activity_home
import com.example.coquille.models.abstracts.Game
import com.example.coquille.utils.Utils

class TurtleGame(level : Level, points :Int): Game("Tortuguita", "¡El pantano se está incendiando! Apaga todos " + "los fuegos y evita a los changuitos antes de que se acabe el tiempo.") {

    //Posiciones totales, de los fuegos y de los tiburones
    var currentPosition = level.initialPos
    var firePositions = level.firePos
    var sharkPositions = level.sharkPos

    //Estados del jugador y del juego
    var currentState = ""
    var playerState = ""

    //Puntos iniciales, y tiempo actual
    var points = points
    var currentTime = 0

    //Tiempo de recarga para las habilidades
    var coolDownWater = 0
    var coolDownSlowMotion = 0
    var coolDownFreezeMonkey = 0

    //La duración de los efectos de las habilidades
    var slowDuration = 0
    var freezeDuration = 0


    //Función que se encarga de evaluar el estado del juego
    fun evaluateGameState(context: Context, gameEnded : Boolean){
        //Si la cantidad de fuegos es 0...
        if (firePositions.size == 0){
            //Notificarle al jugador que ha ganado
            Utils.sendMessage("¡Has ganado!",context )
            //Modificamos el estado
            currentState = "won"
            //Aumentamos la cantidad de puntos ganados.
            points += 50
        }
        //Si el juego ha terminado...
        else if(gameEnded){
            currentState = "finished" //Establecemos el nuevo estado
            //Y le notificamos al usuario
            Utils.sendMessage("¡Se ha terminado el tiempo!",context )
        }
    }


    //Función que se encarga de restarle un '1' a los tiempos de recarga de cada una de las habilidades
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



    //Función que se encarga de administrar las interacciones del usuario
    fun handleInteraction(nextState : String , context: Context, payload : Any?) : Boolean{
        //Si el siguiente estado es el de "move" y el jugador se encuentra moviéndose, o el juego está en slow...
        if((nextState == "move" && (playerState == "moving" || currentState == "slow"))) {
            //Llamamos a la función "nextMovement" y de ser verdadera...
            if (nextMovement(payload as View, context)){
                playerState = "normal" //Modificamos el estado del jugador
                slowDuration = 0 // Establecemos que la duración del efecto de slow es de 0
                return true //Regresamos verdadero
            }
            return false //En cualquier otro caso, regresamos falso

        } else if(nextState == "removeFire"){ //Si el siguiente estado es "removeFire"
            //Evaluamos que la habilidad de agua no se esté recargando, además de llamar a la función removeFire
            if (coolDownWater == 0 && removeFire(payload as View, context)){
                //De ser así, establecemos que la habilidad de agua se recargará en 3 segundos
                coolDownWater = 3
                //Actualizamos el estado del jugador
                playerState = "normal"
                //Regresamos verdadero
                return true
            } else {
                // En cualquier otro caso, regresamos falso
                return false
            }
            //Si el siguiente estado es de "slowMotion"
        } else if(nextState == "slowMotion"){
            //Verificamos que la habilidad no se esté recargando...
            if(coolDownSlowMotion == 0){
                //De ser así, cambiamos el estado de juego, establecemos que en 15 segundos la habilidad volverá a estar disponible y establecemos que la duración del "slow" será de 6 segundos
                currentState = "slow"
                coolDownSlowMotion = 15
                slowDuration = 6
                return true
            }
            //Misma situación que con la habilidad de "slowMotion"
        } else if (nextState == "freezeShark"){
            if (coolDownFreezeMonkey == 0){
                currentState = "freeze"
                coolDownFreezeMonkey = 10
                freezeDuration = 4
                return true
            }
        }
        return false
    }

    //Función que se encarga de evaluar que el siguiente movimiento pueda realizarse.
    fun nextMovement(nextPos : View, context : Context)  : Boolean {
        //Si la posición siguiente es nuestro vecino, o el estado del juego se encuentra en slow...
        if (isANeighbour(nextPos) || currentState == "slow"){
            //Nos preguntamos si esa siguiente posición no está ocuapada por tiburones o fuegos
            if (!firePositions.contains(nextPos) && !sharkPositions.contains(nextPos)){
                //En caso de que no entonces...
                //Obtenemos la info de la posición actual
                var posData = currentPosition.getTag() as Position

                //Accedemos a la posición actual y establecemos que su estado será de posición (Ya que el usuario ya no se encontrará ahí)
                posData.state = "position"
                //Actualizamos su tag.
                currentPosition.setTag(posData)

                //Establecemos la posición actual como la siguiente posición
                currentPosition = nextPos
                //Obtenemos su información y le determinamos que su estado será de "player" ya que el usuario se ha desplazado a esa dirección
                var newData = currentPosition.getTag() as Position
                newData.state = "player"
                currentPosition.setTag(newData)

                return true //Regresamos verdadero
            }
            //En caso de que la posición esté ocupada, se lo notificamos al jugador
            Utils.sendMessage("¡Esa posición está ocupada!", context)
            return false
        }
        //Si la posición no es nuestra vecina, le notificamos al jugador.
        Utils.sendMessage("Estás demasiado lejos :(", context)
        return false
    }

    //Función que se encarga de determinar si podemos remover un fuego
    fun removeFire(firePos : View, context: Context) : Boolean{
        //Nos preguntamos si la posición del fuego es vecina, o si el estado del juego está en "slow", además de preguntarnos si en la posición siguiente sí se encuentra un fuego
        if ((isANeighbour(firePos) || currentState == "slow") && firePositions.contains(firePos)){
            firePositions.remove(firePos) //De ser así, lo removes del arreglo de fuegos

            //Modificamos su estado, para colocarlo como posición
            val fireData = firePos.getTag() as Position
            fireData.state = "position"
            firePos.setTag(fireData)

            //Obtenemos los nuevos puntos, restándoles el tiempo transcurrido.
            val nuevosPuntos = 70 - (currentTime)
            points += nuevosPuntos.toInt() //Actualizamos los puntos totales del juego

            coolDownWater += 3 //Establecemos que la habilidad de agua tardará 3 segundos en recargarse
            // Y le comunicaremos al usuario que ha recibido puntos
            Utils.sendMessage("¡Recibiste :" + nuevosPuntos.toString() + " puntos!", context)
            return true
        }

        return false
    }


    // Función que se encarga de preguntar si la siguiente posición se encuentra aledaña a la posición actual
    fun isANeighbour(positionToEvaluate : View) : Boolean{
        //Obtenemos la información de la posición a evaluar, al igual que la nuestra.
        var posToEvaluateData = positionToEvaluate.getTag() as Position
        var currentPosData = currentPosition.getTag() as Position

        //Obtenemos su columna y fila
        var nextCol = posToEvaluateData.col
        var nextRow = posToEvaluateData.row

        //Al igual que nuestra columna y fila
        var myCol = currentPosData.col
        var myRow = currentPosData.row

        //Nos preguntamos si alguna de estás se encuentra en el mismo eje
        val sameCol = myCol == nextCol
        val sameRow = myRow == nextRow

        //Si alguna de estas se encuentra en el mismo eje...
        if(sameCol || sameRow){
            //Nos preguntamos si se encuentran a un movimiento de distancia (Ya sea hacia adelante, o hacia atrás)
            val sameX = (myCol + 1 == nextCol) || (myCol -1 == nextCol)
            val sameY = (myRow + 1 == nextRow) || (myRow -1 == nextRow)

            //De ser así, regresamos verdadero
            if (sameX || sameY) return true
        }

        return false
    }

    // Función que se encarga de regresar todos los veciones que posee el jugador en su posición actual
    fun returnNeighbour(positionsToEvaluate: MutableList<View> ) : MutableList<View>{
        var mutableList = mutableListOf<View>()
        positionsToEvaluate.forEach{ position ->
            if(isANeighbour(position))
                mutableList.add(position)
        }

        return mutableList
    }
}
