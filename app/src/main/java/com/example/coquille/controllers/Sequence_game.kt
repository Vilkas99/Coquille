package com.example.coquille.controllers

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coquille.R
import kotlin.random.Random

class Sequence_game : AppCompatActivity() {

    private var drawable: Int = R.drawable.ic_circle
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var imageView4: ImageView
    private lateinit var imageView5: ImageView
    private lateinit var imageView6: ImageView
    private lateinit var imageView7: ImageView
    private lateinit var imageView8: ImageView
    private lateinit var gameLostTextView: TextView
    private lateinit var gameWonTextView: TextView
    private lateinit var timer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequence_game)

        imageView = findViewById(R.id.figure1)
        imageView2 = findViewById(R.id.figure2)
        imageView3 = findViewById(R.id.figure3)
        imageView4 = findViewById(R.id.figure4)
        imageView5 = findViewById(R.id.figure5)
        imageView6 = findViewById(R.id.option1)
        imageView7 = findViewById(R.id.option2)
        imageView8 = findViewById(R.id.option3)
        gameWonTextView = findViewById(R.id.gameWonTextView)
        gameLostTextView = findViewById(R.id.gameLostTextView)
        timer = findViewById(R.id.timer)
        genSequence()

        imageView7.setOnClickListener {
            if (imageView7.tag == imageView3.tag){
                Toast.makeText(this, "Pruebita", Toast.LENGTH_LONG).show()
                gameWonTextView.visibility = View.VISIBLE
            }

        }

        object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                timer.setText("Faltan: "+p0 / 1000)
            }

            override fun onFinish() {
                timer.setText("You Died!")
                gameLostTextView.visibility = View.VISIBLE
            }
        }.start()
    }



    fun routeToPreviewSequence(view: View) {
        val intent = Intent(this, preview_sequence::class.java)
        startActivity(intent)
    }

    fun genSequence() {

        val randomIndex = Random.nextInt(0, FigureConstants.words.size)
        val imageToUse = FigureConstants.words[randomIndex]
        val imageResource = resources.getIdentifier(imageToUse, null, packageName)
        val imageToUse2 = FigureConstants.words2[randomIndex]
        val imageResource2 = resources.getIdentifier(imageToUse2, null, packageName)
        val imageToUse3 = FigureConstants.words3[randomIndex]
        val imageResource3 = resources.getIdentifier(imageToUse3, null, packageName)
        imageView.setImageResource(imageResource)
        imageView2.setImageResource(imageResource)
        imageView3.setImageResource(imageResource2)
        imageView3.tag = imageToUse2
        imageView7.tag = imageToUse2
        imageView4.setImageResource(imageResource3)
        imageView5.setImageResource(imageResource3)
        imageView6.setBackgroundResource(imageResource)
        imageView7.setBackgroundResource(imageResource2)
        imageView8.setBackgroundResource(imageResource3)
    }



}