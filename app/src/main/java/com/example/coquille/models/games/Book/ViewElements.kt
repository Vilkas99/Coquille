package com.example.coquille.models.games.Book

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.res.ResourcesCompat
import com.example.coquille.R
import com.google.android.material.button.MaterialButton

//Controller that updates the elements of the game
class ViewElements(context: Context) {
    val context = context

    //Function that creates a radio group with the array of answers correspondent
    fun createRadioGroup(answer : Array<String>) : RadioGroup {
        //Initializing the radio group
        var radioGroupAnswer = RadioGroup(context)
        //Setting the features of the radio group
        radioGroupAnswer.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
        ).apply {
            radioGroupAnswer.orientation = RadioGroup.VERTICAL
            radioGroupAnswer.setPadding(20,20,20,20)
            gravity = Gravity.CENTER
        }
        var j = 0
        //Creating and adding to the radioGroup the correspondent radioButtons with the answers of the question
        for(i in answer){
            var radioButtonAnswer = RadioButton(context)
            radioButtonAnswer.setTypeface(ResourcesCompat.getFont(context, R.font.geosans))
            radioButtonAnswer.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
            ).apply {
                radioButtonAnswer.setText(i)
                radioButtonAnswer.setTextSize(20.0F)
                radioButtonAnswer.buttonTintList = ColorStateList.valueOf(Color.parseColor("#EF767A"))
                radioButtonAnswer.setPadding(20,20,20,40)
                radioButtonAnswer.setId(j)
            }
            radioGroupAnswer.addView(radioButtonAnswer)
            j += 1
        }
        return radioGroupAnswer
    }

    //Function that creates the button for the verification of an answer
    fun createButton() : MaterialButton {
        //Initializing the button
        var buttonConfirm = MaterialButton(context)
        //Setting the features of the button
        buttonConfirm.setTypeface(ResourcesCompat.getFont(context, R.font.geosans))
        buttonConfirm.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
        ).apply {
            buttonConfirm.elevation = 20.0F
            buttonConfirm.setText("Verificar")
            buttonConfirm.setTextSize(15.0F)
            buttonConfirm.setTextColor(Color.parseColor("#000000"))
            buttonConfirm.setBackgroundColor(Color.parseColor("#FFE68F"))
            buttonConfirm.cornerRadius = 20
            gravity = Gravity.CENTER
        }

        return buttonConfirm
    }

    //Function that removes the elements created for the question to continue with the reading
    fun backToStory(layout : LinearLayout, radioGroup: RadioGroup, button : MaterialButton){
        layout.orientation = LinearLayout.VERTICAL
        layout.removeView(radioGroup)
        layout.removeView(button)
    }

}