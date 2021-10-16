package com.example.coquille.models.games.Colorful

class Colorful constructor(timer: Int, points: Int){

    var timer = timer
    var points = points

    //Función para manejar los puntos; aumentando los puntos y regresando el valor.
    fun calculatePoints(): Int{
        points += 23
        println("Correcto: " + points)

        return points
    }

}