package com.example.coquille.models.games.Turtle

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.R
import com.example.coquille.utils.Utils

class Level {
    var positions = mutableListOf<View>()
    var firePos = mutableListOf<View>()
    var sharkPos = mutableListOf<View>()
    lateinit var initialPos : View


    fun createElements(myLayout: ConstraintLayout) {
        myLayout.forEach { view ->
            if (view.tag != null){
                val tag = view.getTag() as String
                if(tag.contains("fire")){
                    val (row, col) = getRowAndCol(tag.slice(4 until tag.length))
                    val fireNewTag = Position(Utils.getId(view), "fire", row, col )
                    firePos.add(view)
                    view.setTag(fireNewTag)
                    Utils.createAnimation(view as LottieAnimationView, R.raw.fire_animation, 100, -50)
                } else if(tag.contains("shark")){
                    val (row, col) = getRowAndCol(tag.substring(5))
                    val sharkNewTag = Position(Utils.getId(view), "monkey", row, col)
                    view.setTag(sharkNewTag)
                    sharkPos.add(view)
                    Utils.createAnimation(view as LottieAnimationView, R.raw.shark, 100, -50)
                } else {
                    val (row, col) = getRowAndCol(tag)
                    val id = Utils.getId(view)
                    val position = Position(id, "position", row, col)
                    view.setTag(position)

                    if (id == "com.example.coquille:id/mainPos"){
                        initialPos = view
                        val position = Position(id, "player", row, col)
                        view.setTag(position)
                        Utils.createAnimation(initialPos as LottieAnimationView, R.raw.turtle, 100, -50)
                    }

                }
                positions.add(view)
            }


        }
    }

    fun getRowAndCol(tag : String) : Pair<Int, Int>{
        val row = (tag[0].toInt() - '0'.toInt())
        val col = (tag[2].toInt() - '0'.toInt())

        val result = Pair(row, col)

        return result
    }

}