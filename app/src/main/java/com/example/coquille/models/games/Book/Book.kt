package com.example.coquille.models.games.Book


class Book(points : Int) {
    var points = points



    fun finishedGame(pagesLevel: Array<String>, questionIndex : Int) : Boolean{
        return pagesLevel.size == questionIndex
    }

    fun updatePoints(puntos : Int){
        points += puntos
    }

}
