package com.example.coquille.controllers

import android.os.Bundle
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


class Sequence_stage1 : Fragment() {

    lateinit var figura1: ImageView
    lateinit var figura2: ImageView
    lateinit var figura3: ImageView
    lateinit var option1: ImageButton
    lateinit var option2: ImageButton
    lateinit var option3: ImageButton
    lateinit var win: TextView
    lateinit var lost: TextView
    var passed: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sequence_stage, container, false)

        figura1 = rootView.findViewById(R.id.figure1)
        figura2 = rootView.findViewById(R.id.figure2)
        figura3 = rootView.findViewById(R.id.figure3)
        option1 = rootView.findViewById(R.id.option1)
        option2 = rootView.findViewById(R.id.option2)
        option3 = rootView.findViewById(R.id.option3)
        win = rootView.findViewById(R.id.gameWonTextView)
        lost = rootView.findViewById(R.id.gameLostTextView)

        genSequence()

        var puntos: Int = 0

        option1.setOnClickListener {
            if(option1.tag == figura1.tag){
                puntos += 50
                println("Esto son tus puntos: " + puntos)
                genSequence()

            }
        }

        option2.setOnClickListener {
            if(option2.tag == figura1.tag){
                puntos += 50
                println("Esto son tus puntos: " + puntos)
                genSequence()
            }
        }

        option3.setOnClickListener {
            if(option3.tag == figura1.tag){
                puntos += 50
                println("Esto son tus puntos: " + puntos)
                genSequence()
            }
        }

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

}