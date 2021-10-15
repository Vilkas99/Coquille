package com.example.coquille.controllers.Sequence.levels

import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.test.core.app.ApplicationProvider
import com.example.coquille.R
import com.example.coquille.utils.FigureConstants
import kotlin.random.Random
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.coquille.models.Sequence


class Sequence_stage3 : Fragment(R.layout.fragment_sequence_stage3) {

    lateinit var figura1: ImageView
    lateinit var figura2: ImageView
    lateinit var figura3: ImageView
    lateinit var figura4: ImageView
    lateinit var figura5: ImageView
    lateinit var figura6: ImageView
    lateinit var figura7: ImageView
    lateinit var figura8: ImageView
    lateinit var figura9: ImageView
    lateinit var figura10: ImageView
    lateinit var figura11: ImageView
    lateinit var option1: ImageButton
    lateinit var option2: ImageButton
    lateinit var option3: ImageButton
    lateinit var gameState: Sequence
    lateinit var puntos: TextView
    lateinit var timer: TextView
    var hola: String = ""
    var adios: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sequence_stage3, container, false)

        figura1 = rootView.findViewById(R.id.figure1)
        figura2 = rootView.findViewById(R.id.figure2)
        figura3 = rootView.findViewById(R.id.figure3)
        figura4 = rootView.findViewById(R.id.figure4)
        figura5 = rootView.findViewById(R.id.figure5)
        figura6 = rootView.findViewById(R.id.figure6)
        figura7 = rootView.findViewById(R.id.figure7)
        figura8 = rootView.findViewById(R.id.figure8)
        figura9 = rootView.findViewById(R.id.figure9)
        figura10 = rootView.findViewById(R.id.figure10)
        figura11 = rootView.findViewById(R.id.figure11)
        option1 = rootView.findViewById(R.id.option1)
        option2 = rootView.findViewById(R.id.option2)
        option3 = rootView.findViewById(R.id.option3)
        puntos = rootView.findViewById(R.id.puntos)
        timer = rootView.findViewById(R.id.timer2)

        genSequence()

        gameState = Sequence(10, 0, figura1, figura2, figura3, option1, option2, option3, "O P T I M O", "B O L U D O")

        validateOption(figura1, option1)
        validateOption2(figura1, option2)
        validateOption3(figura1, option3)

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

        figura1.setImageResource(imageResource3)
        figura2.setImageResource(imageResource3)
        figura3.setImageResource(imageResource2)
        figura4.setImageResource(imageResource)
        figura5.setImageResource(imageResource)
        figura6.setImageResource(imageResource3)
        figura7.setImageResource(imageResource3)
        figura8.setImageResource(imageResource2)
        figura9.setImageResource(imageResource)
        figura10.setImageResource(imageResource)
        figura11.setImageResource(imageResource3)

        //Se repite el procedimiento
        figura1.tag = imageResource3
        option1.tag = list2[0]
        option2.tag = list2[1]
        option3.tag = list2[2]

    }

    fun genSequence2( ) {

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

        figura1.setImageResource(imageResource3)
        figura2.setImageResource(imageResource3)
        figura3.setImageResource(imageResource3)
        figura4.setImageResource(imageResource2)
        figura5.setImageResource(imageResource2)
        figura6.setImageResource(imageResource)
        figura7.setImageResource(imageResource3)
        figura8.setImageResource(imageResource3)
        figura9.setImageResource(imageResource3)
        figura10.setImageResource(imageResource2)
        figura11.setImageResource(imageResource2)

        //Se repite el procedimiento
        figura1.tag = imageResource
        option1.tag = list2[0]
        option2.tag = list2[1]
        option3.tag = list2[2]

    }

    fun genSequence3( ) {

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
        figura2.setImageResource(imageResource)
        figura3.setImageResource(imageResource3)
        figura4.setImageResource(imageResource2)
        figura5.setImageResource(imageResource)
        figura6.setImageResource(imageResource)
        figura7.setImageResource(imageResource3)
        figura8.setImageResource(imageResource2)
        figura9.setImageResource(imageResource)
        figura10.setImageResource(imageResource)
        figura11.setImageResource(imageResource3)

        //Se repite el procedimiento
        figura1.tag = imageResource2
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

    fun validateOption2(image: ImageView, option: ImageButton) {
        option.setOnClickListener {
            if(option.tag == image.tag){
                var puntitos: Int = 0
                puntitos = gameState.calculatePoints()
                updatePuntuation(puntitos)
                genSequence2()
            }
        }

    }

    fun validateOption3(image: ImageView, option: ImageButton) {
        option.setOnClickListener {
            if(option.tag == image.tag){
                var puntitos: Int = 0
                puntitos = gameState.calculatePoints()
                updatePuntuation(puntitos)
                genSequence3()
            }
        }

    }

    fun updatePuntuation(points: Int){
        puntos.text = points.toString()
    }

}