package com.example.coquille.models

class User constructor(userName: String, password: String, profilePic: String, points: Int, collectables:Array<Collectable>, settings: Settings) {
    var userName = userName
    var password = password
    var profilePic = profilePic
    var points = points
    var collectables = collectables
    var settings = settings


}