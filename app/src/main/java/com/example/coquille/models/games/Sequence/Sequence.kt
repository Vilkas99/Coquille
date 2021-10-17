package com.example.coquille.models.games.Sequence

import android.widget.ImageButton
import android.widget.ImageView

//Constructor inicial de la clase de Secuencia
class Sequence constructor(timer: Int, points: Int, figure1: ImageView, figure2: ImageView, figure3: ImageView, option1: ImageButton, option2: ImageButton, option3: ImageButton, gameWon: String, gameLost: String) {

    //Declaración de las variables
    var timer = timer
    var points = points
    var figure1 = figure1
    var figure2 = figure2
    var figure3 = figure3
    var option1 = option1
    var option2 = option2
    var option3 = option3
    var gameLost = gameLost
    var gameWon = gameWon

    //Función para manejar los puntos; aumentando los puntos y regresando el valor.
    fun calculatePoints(): Int{
        points += 23
        println("Correcto: " + points)

        return points
    }



}