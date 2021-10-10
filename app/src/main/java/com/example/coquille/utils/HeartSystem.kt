package com.example.coquille.utils

import android.widget.ImageView
import com.example.coquille.R

class HeartSystem constructor(inititalHearts: Int, hearts: List<ImageView>) {
    var currentHearts = inititalHearts
    val hearts = hearts

    fun reduceHeart(){
        currentHearts -= 1
        if (currentHearts == 0){
            hearts[2].setImageResource(R.drawable.ic_empy_hearth)
        }
        else if (currentHearts == 1){
            hearts[1].setImageResource(R.drawable.ic_empy_hearth)
        } else {
            hearts[0].setImageResource(R.drawable.ic_empy_hearth)
        }
    }

    fun addHeart(){
        currentHearts += 1
        if (currentHearts == 3){
            hearts[0].setImageResource(R.drawable.ic_heart)
        } else if(currentHearts == 2) {
            hearts[1].setImageResource(R.drawable.ic_heart)
        } else {
            hearts[2].setImageResource(R.drawable.ic_heart)
        }
    }

}