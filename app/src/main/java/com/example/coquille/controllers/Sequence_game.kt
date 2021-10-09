package com.example.coquille.controllers

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coquille.R
import com.example.coquille.databinding.ActivitySequenceGameBinding
import kotlin.random.Random
import com.example.coquille.models.Sequence
import com.example.coquille.utils.FigureConstants

class Sequence_game : AppCompatActivity() {

    private lateinit var binding: ActivitySequenceGameBinding
    val infoFragment = Sequence_stage1()
    val infoFragment2 = Sequence_stage2()
    val infoFragment3 = Sequence_stage3()
    lateinit var sequence: Sequence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequence_game)
        binding = ActivitySequenceGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.host, infoFragment)
                commit()
            }

        val sequence = Sequence(
            0,
            0,
            R.drawable.ic_square,
            R.drawable.ic_square,
            R.drawable.ic_triangle,
            R.drawable.ic_circle,
            R.drawable.ic_circle,
            R.drawable.ic_square,
            R.drawable.ic_triangle,
            R.drawable.ic_circle,
            "Haz ganado",
            "Haz perido:("
        )

        timerSequence(10000, 1000)

        println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        println(infoFragment.passed)


    }

    fun passStage(pass: Boolean){
        var pass = true.apply {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.host, infoFragment2)
                commit()
                timerSequence(10000,1000)
            }
            println(this)
        }
    }

    fun passStage2(pass: Boolean){
        var pass = true.apply {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.host, infoFragment3)
                commit()
                binding.timer.text = "10"
                timerSequence(10000,1000)
            }
            println(this)
        }
    }


    fun timerSequence(time: Long, intervalo: Long){
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                binding.timer.setText("Faltan: " + p0 / 1000)
            }

            override fun onFinish() {
                binding.timer.setText("You Died!")
                infoFragment.lost.visibility = View.VISIBLE
            }
        }.start()
    }



    /*fun genSequence() {

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
    }*/

    fun routeToPreviewScreen(view: View) {
        val player = MediaPlayer.create(this, R.raw.sequence_sound)
        player.start()
        val intent = Intent(this, preview_sequence::class.java)
        startActivity(intent)
    }

}