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
            "Haz perdido:("
        )

        timerSequence(20000, 1000)

        println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        println(infoFragment.passed)


    }

    fun passStage(pass: Boolean){
        var pass = true.apply {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.host, infoFragment2)
                commit()
            }
            println(this)
        }
    }

    fun passStage2(pass: Boolean){
        var pass = true.apply {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.host, infoFragment3)
                commit()
            }
            println(this)
        }
    }

    fun timerSequence(time: Long, intervalo: Long){
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                binding.timer.setText("00:" + p0 / 1000)
            }

            override fun onFinish() {
                infoFragment.lost.visibility = View.VISIBLE
            }
        }.start()
    }

    fun routeToPreviewScreen(view: View) {
        val player = MediaPlayer.create(this, R.raw.sequence_sound)
        player.start()
        val intent = Intent(this, preview_sequence::class.java)
        startActivity(intent)
    }

    fun routeToPreview(view : View){
        val intent = Intent(this, PreviewGame::class.java)
        val nameGame :String = "sequence"
        val b : Bundle = Bundle()
        b.putString("indexArray", nameGame)
        intent.putExtras(b)
        startActivity(intent)
    }

}