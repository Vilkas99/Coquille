package com.example.coquille.models

class Settings constructor(breakRecomendations:Boolean, notifications: Boolean, limitPlayTime: Boolean, music: Boolean, sfx: Boolean) {
    val breakRecomendations = breakRecomendations
    val notifications = notifications
    val limitPlayTime = limitPlayTime
    val music = music
    val sfx = sfx
}