package com.example.coquille.models.abstracts

abstract class Game constructor(titleGame: String, gameIcon: String, description: String) {
    val title = titleGame
    val gameIcon = gameIcon
    val description = description
}