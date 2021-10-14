package com.example.coquille.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
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
        val handler = Handler(Looper.getMainLooper())


        val title =intent.getStringExtra("titlePopup").toString()
        val body =intent.getStringExtra("bodyPopup").toString()
        val customized = intent.getStringExtra("customized").toString()

        if(customized.length!=0){
            binding.gemaGanada.visibility = View.GONE
            setInfo(title, customized)
        } else{
            binding.gemaGanada.visibility = View.VISIBLE
            setInfo(title, body)
        }

        handler.postDelayed({this.finish()}, 2000)



    }



    fun setInfo(title : String, body : String){
        binding.textResultados.setText(title)
        binding.textGemasGanadas.setText(body)
    }
}