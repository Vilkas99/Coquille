package com.example.coquille.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import com.example.coquille.R
import kotlin.math.roundToInt

class Popup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)

        var screenMeasures = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(screenMeasures)

        var width = screenMeasures.widthPixels
        var height = screenMeasures.heightPixels

        window.setLayout((width*0.8).roundToInt(), (height*0.31).roundToInt())
    }
}