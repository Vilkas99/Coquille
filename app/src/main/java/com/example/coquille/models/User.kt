package com.example.coquille.models

class User constructor(userName: String, password: String, profilePic: Int, points: Int, collectables:MutableList<Collectable>, settings: Settings) {
    var userName = userName
    var password = password
    var profilePic = profilePic
    var points = points
    var collectables = collectables
    var settings = settings
    var lastPlayedDate: DateInt = DateInt()

}