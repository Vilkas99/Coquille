package com.example.coquille.models.Book


class Book(points : Int, hints : Int) {
    var points = points
    var hints = hints



    fun finishedGame(pagesLevel: Array<String>, questionIndex : Int) : Boolean{
        return pagesLevel.size == questionIndex
    }

}
