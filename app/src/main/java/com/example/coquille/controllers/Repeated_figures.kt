package com.example.coquille.controllers

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.LinearLayout.LayoutParams;
import android.view.Gravity
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.coquille.R
import com.example.coquille.databinding.ActivityRepeatedFiguresBinding
import com.example.coquille.utils.FigureConstants
import kotlin.random.Random

class Repeated_figures : AppCompatActivity() {

    private lateinit var binding: ActivityRepeatedFiguresBinding
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repeated_figures)
        binding = ActivityRepeatedFiguresBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.iimagen1.setOnClickListener {
            binding.iimagen1.setImageDrawable(resources.getDrawable(R.drawable.ic_spider))
        }

        genRandomPosition(binding.iimagen1)

    }

    fun genRandomPosition(image: ImageView) {
        val randomIndex = Random.nextInt(0, FigureConstants.words.size)
        val imageToUse = FigureConstants.words[randomIndex]
        val imageResource = resources.getIdentifier(imageToUse, null, packageName)
        image.setImageResource(imageResource)
    }

    fun genRandomPosition2(image: ImageView) {
        val randomIndex = Random.nextInt(0, FigureConstants.words.size)
        val imageToUse2 = FigureConstants.words2[randomIndex]
        val imageResource2 = resources.getIdentifier(imageToUse2, null, packageName)
        image.setImageResource(imageResource2)
    }

    fun genRandomPosition3(image: ImageView) {
        val randomIndex = Random.nextInt(0, FigureConstants.words.size)
        val imageToUse3 = FigureConstants.words3[randomIndex]
        val imageResource3 = resources.getIdentifier(imageToUse3, null, packageName)
        image.setImageResource(imageResource3)
    }

}