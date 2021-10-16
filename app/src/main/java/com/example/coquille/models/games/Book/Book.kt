package com.example.coquille.models.games.Book

//Controller used to handle the points of the game
class Book(points : Int) {
    var points = points

    //Function that determines if the reading is finished or not
    fun finishedGame(pagesLevel: Array<String>, questionIndex : Int) : Boolean{
        return pagesLevel.size == questionIndex
    }
    //Function that updates the user's points
    fun updatePoints(puntos : Int){
        points += puntos
    }

}
