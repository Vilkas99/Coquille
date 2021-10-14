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

        //Generamos un nuevo nivel con un tiempo total de 30 segundos, un rango de 5 segundos para la aparición de tiburones, y uno de 8 para la de fuegos
        val level = Level(30000, 5, 8)

        //Ejecutamos la función "createElements" de nuestro nivel, brindándole el id del Layout en cuestión
        level.createElements(rootView.findViewById(R.id.level1Main))

        //Llamamos a la función setCurrentLevel de la actividad "Turtle" pasándole el nivel en cuestión
        (activity as Turtle).setCurrentLevel(level)

        return rootView
    }
}