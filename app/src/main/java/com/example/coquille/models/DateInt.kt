package com.example.coquille.models

import java.time.LocalDateTime

class DateInt() {

    var year : Int = LocalDateTime.now().year
    var month : Int = LocalDateTime.now().monthValue
    var day : Int = LocalDateTime.now().dayOfMonth
    var hour : Int = LocalDateTime.now().hour
    var minute : Int = LocalDateTime.now().minute

}