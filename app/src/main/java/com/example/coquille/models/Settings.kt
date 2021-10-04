package com.example.coquille.models

class Settings constructor(breakRecommendations:Boolean = false, notifications: Boolean = true, limitPlayTime: Boolean = false, music: Boolean = true, sfx: Boolean = true) {
    val breakRecommendations = breakRecommendations
    val notifications = notifications
    val limitPlayTime = limitPlayTime
    val music = music
    val sfx = sfx
}