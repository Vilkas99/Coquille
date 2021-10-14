package com.example.coquille.controllers.Sequence

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coquille.R
import com.example.coquille.controllers.PreviewGame
import com.example.coquille.controllers.Sequence.levels.Sequence_stage1
import com.example.coquille.controllers.Sequence.levels.Sequence_stage2
import com.example.coquille.controllers.Sequence.levels.Sequence_stage3
import com.example.coquille.databinding.ActivitySequenceGameBinding
import com.example.coquille.models.Sequence
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils

class Sequence_game() : AppCompatActivity() {

    private val mySharedPreferences : MySharedPreferences = MySharedPreferences(this)
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

        chooseLevel()

    }

    fun chooseLevel() {
        var currentLevel = infoFragment as Fragment
        val bundle =intent.getStringExtra("sequenceLevel").toString()

        when(bundle){
            "level1" ->
                currentLevel = infoFragment


            "level2" ->
                currentLevel = infoFragment2


            "level3" ->
                currentLevel = infoFragment3

        }

        if(currentLevel == infoFragment2){
            supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.host, currentLevel)
                    commit()
                }
            timerSequenceLvl2(15000, 1000, infoFragment2.hola, infoFragment2.adios)
        }
        else if(currentLevel == infoFragment){
            supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.host, currentLevel)
                    commit()
                }
            timerSequenceLvl1(15000, 1000, infoFragment.hola, infoFragment.adios)
        }
        else if(currentLevel == infoFragment3){
            supportFragmentManager.beginTransaction()
                .apply {
                    replace(R.id.host, currentLevel)
                    commit()
                }
            timerSequenceLvl3(15000, 1000, infoFragment.hola, infoFragment.adios)
        }

    }

    fun routeToPreview(view : View){
        val intent = Intent(this, PreviewGame::class.java)
        val nameGame :String = "sequence"
        val b : Bundle = Bundle()
        b.putString("indexArray", nameGame)
        intent.putExtras(b)
        startActivity(intent)
    }

    fun timerSequenceLvl1(time: Long, intervalo: Long, yolo: String, adiu: Int){
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                infoFragment.timer.setText("00:" + p0 / 1000)
            }

            override fun onFinish() {
                infoFragment.lost.visibility = View.VISIBLE
                infoFragment.option1.setOnClickListener(null)
                infoFragment.option2.setOnClickListener(null)
                infoFragment.option3.setOnClickListener(null)
                infoFragment.hola = infoFragment.puntos.text.toString()
                infoFragment.adios = infoFragment.hola.toInt()
                userPoints()

            }
        }.start()
    }

    fun timerSequenceLvl2(time: Long, intervalo: Long, yolo: String, adiu: Int){
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                infoFragment2.timer.setText("00:" + p0 / 1000)
            }

            override fun onFinish() {
                infoFragment2.option1.setOnClickListener(null)
                infoFragment2.option2.setOnClickListener(null)
                infoFragment2.option3.setOnClickListener(null)
                infoFragment2.hola = infoFragment2.puntos.text.toString()
                infoFragment2.adios = infoFragment2.hola.toInt()
                userPoints2()

            }
        }.start()
    }

    fun timerSequenceLvl3(time: Long, intervalo: Long, yolo: String, adiu: Int){
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                infoFragment3.timer.setText("00:" + p0 / 1000)
            }

            override fun onFinish() {
                infoFragment3.option1.setOnClickListener(null)
                infoFragment3.option2.setOnClickListener(null)
                infoFragment3.option3.setOnClickListener(null)
                infoFragment3.hola = infoFragment3.puntos.text.toString()
                infoFragment3.adios = infoFragment3.hola.toInt()
                userPoints3()

            }
        }.start()
    }

    fun userPoints(){
        val user = Utils.getCurrentUser(this)
        user.points += infoFragment.adios
        mySharedPreferences.editData(user, "currentUser")
    }

    fun userPoints2(){
        val user = Utils.getCurrentUser(this)
        user.points += infoFragment2.adios
        mySharedPreferences.editData(user, "currentUser")
    }

    fun userPoints3(){
        val user = Utils.getCurrentUser(this)
        user.points += infoFragment3.adios
        mySharedPreferences.editData(user, "currentUser")
    }

}