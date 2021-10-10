package com.example.coquille.utils

import android.view.View
import android.widget.TextView
import com.example.coquille.R

//Ver implementación en MemoryGameActivity como referencia
class PointsSystem(initialPoints : Int, var pointsView : View) {

    var currentPoints = initialPoints
    var pointsTextView : TextView = pointsView.findViewById(R.id.currentPoints)

    init{
        pointsTextView.text = initialPoints.toString()
    }

    fun addPoints(howManyPoints : Int){

        currentPoints += howManyPoints
        pointsTextView.text = currentPoints.toString()

    }

    fun subtractPoints(howManyPoints: Int){

        currentPoints -= howManyPoints
        pointsTextView.text = currentPoints.toString()

    }

}