package com.example.coquille.controllers

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.coquille.R
import com.example.coquille.utils.FigureConstants
import kotlin.random.Random
import com.example.coquille.models.Sequence
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils


class Sequence_stage1 : Fragment() {

    lateinit var figura1: ImageView
    lateinit var figura2: ImageView
    lateinit var figura3: ImageView
    lateinit var option1: ImageButton
    lateinit var option2: ImageButton
    lateinit var option3: ImageButton
    lateinit var puntos: TextView
    lateinit var win: TextView
    lateinit var lost: TextView
    lateinit var timer: TextView
    lateinit var gameState: Sequence
    var passed: Boolean = false
    var hola: String = ""
    var adios: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sequence_stage, container, false)

        figura1 = rootView.findViewById(R.id.figure1)
        figura2 = rootView.findViewById(R.id.figure2)
        figura3 = rootView.findViewById(R.id.figure3)
        option1 = rootView.findViewById(R.id.option1)
        option2 = rootView.findViewById(R.id.option2)
        option3 = rootView.findViewById(R.id.option3)
        puntos = rootView.findViewById(R.id.puntos)
        win = rootView.findViewById(R.id.gameWonTextView)
        lost = rootView.findViewById(R.id.gameLostTextView)
        timer = rootView.findViewById(R.id.timer)

        genSequence()

        var puntos: Int = 0


        gameState = Sequence(10, 0, figura1, figura2, figura3, option1, option2, option3, "O P T I M O", "B O L U D O")

        validateOption(figura1, option1)
        validateOption(figura1, option2)
        validateOption(figura1, option3)

        timerSequence(20000, 1000, hola, adios)

        return rootView
    }


    fun genSequence( ) {

        val randomIndex = Random.nextInt(0, FigureConstants.words.size)

        val imageToUse = FigureConstants.words[randomIndex] //3 "varawiiii!!!"
        val imageResource = resources.getIdentifier(imageToUse, null, context?.packageName)

        val imageToUse2 = FigureConstants.words2[randomIndex] //2 "pocion"
        val imageResource2 = resources.getIdentifier(imageToUse2, null, context?.packageName)

        val imageToUse3 = FigureConstants.words3[randomIndex] //1 "araña"
        val imageResource3 = resources.getIdentifier(imageToUse3, null, context?.packageName)

        // Se llena la lista mutable con los valores de los imagesResourse
        val list = mutableListOf(imageResource, imageResource2, imageResource3)
        // ["pocion", "araña", "varawiiii!!!"]
        val list2 = mutableListOf<Int>()

        while (list.size > 0){

            //Se genera un numero aleatorio 0 al tamaño de la listaMutable [2]
            val randomIndex2 = Random.nextInt(0, list.size)
            //Se obtiene ese valor de la lista mutable "araña"
            val comparacion = list[randomIndex2]
            //Cuando se obtiene el numero se remueve de la lista
            list.removeAt(randomIndex2)
            // ["pocion", "varawiiii!!!"]
            // Se agrega el valor removido "araña" a un boton junto a su tag
            list2.add(comparacion)

        }

        option1.setBackgroundResource(list2[0])
        option2.setBackgroundResource(list2[1])
        option3.setBackgroundResource(list2[2])

        figura1.setImageResource(imageResource)
        figura2.setImageResource(imageResource3)
        figura3.setImageResource(imageResource2)

        //Se repite el procedimiento
        figura1.tag = imageResource
        option1.tag = list2[0]
        option2.tag = list2[1]
        option3.tag = list2[2]

    }

    fun validateOption(image: ImageView, option: ImageButton) {
        option.setOnClickListener {
            if(option.tag == image.tag){
                var puntitos: Int = 0
                puntitos = gameState.calculatePoints()
                updatePuntuation(puntitos)
                genSequence()
            }
        }

    }

    fun updatePuntuation(points: Int){
        puntos.text = points.toString()
    }

    fun userPoints(){
        val user = Utils.getCurrentUser(context.applicationContext)
        user.points += adios
        mySharedPreferences.editData(user, "currentUser")
    }

    fun timerSequence(time: Long, intervalo: Long, yolo: String, adiu: Int){
        object : CountDownTimer(time, intervalo) {
            override fun onTick(p0: Long) {
                timer.setText("00:" + p0 / 1000)
            }

            override fun onFinish() {
                lost.visibility = View.VISIBLE
                option1.setOnClickListener(null)
                option2.setOnClickListener(null)
                option3.setOnClickListener(null)
                hola = puntos.text.toString()
                adios = hola.toInt()
                println(hola)

            }
        }.start()
    }

}