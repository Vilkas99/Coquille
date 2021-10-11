package com.example.coquille.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
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
    lateinit var figura4: ImageView
    lateinit var figura5: ImageView
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
        figura4 = rootView.findViewById(R.id.figure4)
        figura5 = rootView.findViewById(R.id.figure5)
        option1 = rootView.findViewById(R.id.option1)
        option2 = rootView.findViewById(R.id.option2)
        option3 = rootView.findViewById(R.id.option3)
        win = rootView.findViewById(R.id.gameWonTextView)
        lost = rootView.findViewById(R.id.gameLostTextView)

        genSequence()

        option2.setOnClickListener {
            if(option2.tag == figura2.tag){
                //Toast.makeText(this, "Pruebita", Toast.LENGTH_LONG).show()
                win.visibility = View.VISIBLE
                (activity as Sequence_game).passStage(passed)
            }
        }

        return rootView
    }


    fun genSequence( ) {

        val randomIndex = Random.nextInt(0, FigureConstants.words.size)
        val imageToUse = FigureConstants.words[randomIndex]
        val imageResource = resources.getIdentifier(imageToUse, null, context?.packageName)
        val imageToUse2 = FigureConstants.words2[randomIndex]
        val imageResource2 = resources.getIdentifier(imageToUse2, null, context?.packageName)
        val imageToUse3 = FigureConstants.words3[randomIndex]
        val imageResource3 = resources.getIdentifier(imageToUse3, null, context?.packageName)
        figura1.setImageResource(imageResource)
        figura2.setImageResource(imageResource)
        figura3.setImageResource(imageResource2)
        figura4.setImageResource(imageResource3)
        figura5.setImageResource(imageResource3)
        option1.setBackgroundResource(imageResource)
        option2.setBackgroundResource(imageResource2)
        option3.setBackgroundResource(imageResource3)
        figura2.tag = imageResource2
        option2.tag = imageResource2

    }

}