package com.example.coquille.models

class Settings (breakRecommendations:Boolean = false, notifications: Boolean = true, limitPlayTime: Boolean = false, music: Boolean = true, sfx: Boolean = true) {

    var breakRecommendations = breakRecommendations
    var notifications = notifications
    var limitPlayTime = limitPlayTime
    var timeLimit = 10
    var music = music
    var sfx = sfx


}