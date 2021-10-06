package com.example.coquille.controllers

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coquille.R
import com.example.coquille.databinding.ActivitySequenceGameBinding
import kotlin.random.Random
import com.example.coquille.models.Sequence
import com.example.coquille.utils.FigureConstants

class Sequence_game : AppCompatActivity() {

    private lateinit var binding: ActivitySequenceGameBinding
    lateinit var sequence: Sequence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequence_game)
        binding = ActivitySequenceGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val sequence = Sequence(0, 0, R.drawable.ic_square, R.drawable.ic_square, R.drawable.ic_triangle, R.drawable.ic_circle, R.drawable.ic_circle, R.drawable.ic_square, R.drawable.ic_triangle, R.drawable.ic_circle, "Haz ganado", "Haz perido:(")

        binding.gameWonTextView.text = sequence.gameWon
        binding.gameLostTextView.text = sequence.gameLost

        genSequence()

        binding.option2.setOnClickListener {
            if (binding.option2.tag == binding.figure2.tag){
                Toast.makeText(this, "Pruebita", Toast.LENGTH_LONG).show()
                binding.gameWonTextView.visibility = View.VISIBLE
            }

        }

        object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                binding.timer.setText("Faltan: "+p0 / 1000)
            }

            override fun onFinish() {
                binding.timer.setText("You Died!")
                binding.gameLostTextView.visibility = View.VISIBLE
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
        binding.figure1.setImageResource(imageResource)
        binding.figure2.setImageResource(imageResource)
        binding.figure3.setImageResource(imageResource2)
        binding.figure4.setImageResource(imageResource3)
        binding.figure5.setImageResource(imageResource3)
        binding.option1.setBackgroundResource(imageResource)
        binding.option2.setBackgroundResource(imageResource2)
        binding.option3.setBackgroundResource(imageResource3)
        binding.figure2.tag = imageResource2
        binding.option2.tag = imageResource2
    }



}