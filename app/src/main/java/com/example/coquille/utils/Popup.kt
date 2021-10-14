package com.example.coquille.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import com.example.coquille.R
import com.example.coquille.controllers.BookGame
import com.example.coquille.databinding.ActivityPopupBinding
import kotlin.math.roundToInt

class Popup : AppCompatActivity(){
    private lateinit var binding: ActivityPopupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopupBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var screenMeasures = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(screenMeasures)

        var width = screenMeasures.widthPixels
        var height = screenMeasures.heightPixels

        window.setLayout((width*0.8).roundToInt(), (height*0.31).roundToInt())

        val title =intent.getStringExtra("titlePopup").toString()
        val body =intent.getStringExtra("bodyPopup").toString()
        setInfo(title, body)


    }

    fun hidePopup(view: View)  {
        this.finish()
    }

    fun setInfo(title : String, body : String){
        binding.textResultados.setText(title)
        binding.textGemasGanadas.setText(body)
    }
}