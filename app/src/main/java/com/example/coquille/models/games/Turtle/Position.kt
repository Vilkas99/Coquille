package com.example.coquille.models.games.Turtle

import android.view.View
import com.example.coquille.utils.Utils

class Position(view:View, state : String) {
    var id = Utils.getId(view)
    var state = state
    var neighbours = mutableListOf<Position>()
    var viewReference = view

    fun addNeighbour(pos : Position){
        neighbours.add(pos)
    }

}