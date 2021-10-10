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


class repeated_stage1 : Fragment() {

    lateinit var figura1: ImageButton
    lateinit var figura2: ImageButton
    lateinit var figura3: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_repeated_stage1, container, false)

        figura1 = rootView.findViewById(R.id.uncolorspider)
        figura2 = rootView.findViewById(R.id.uncolorpotion1)
        figura3 = rootView.findViewById(R.id.uncolorpotion2)

        figura1.setOnClickListener{
            figura1.setImageResource(R.drawable.ic_dragon)
        }

        return rootView
    }


}