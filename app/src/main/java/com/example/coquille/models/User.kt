package com.example.coquille.models

class User constructor(userName: String, password: String, profilePic: String, points: Int, collectables:Array<Collectable>) {
    val userName = userName
    val password = password
    val profilePic = profilePic
    val points = points
    val collectables = collectables
}