package com.example.coquille.controllers.Turtle.levels

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coquille.R
import com.example.coquille.controllers.Turtle.Turtle
import com.example.coquille.models.games.Turtle.Level

class TurtleLvl2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_turtle_lvl2, container, false)

        val level = Level(30000, 5, 8)
        level.createElements(rootView.findViewById(R.id.level1Main))

        (activity as Turtle).setCurrentLevel(level)

        return rootView
    }
}