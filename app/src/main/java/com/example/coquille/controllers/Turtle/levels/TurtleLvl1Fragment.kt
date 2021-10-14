package com.example.coquille.controllers.Turtle.levels

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coquille.R
import com.example.coquille.controllers.Turtle.Turtle
import com.example.coquille.models.games.Turtle.Level

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [turtle_level1.newInstance] factory method to
 * create an instance of this fragment.
 */
class TurtleLevel1_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_turtle_level1, container, false)

        //Generamos un nuevo nivel con un tiempo total de 50 segundos, un rango de 10 segundos para la aparición de tiburones, y uno de 15 para la de fuegos
        val level = Level(50000, 10, 15)

        //Ejecutamos la función "createElements" de nuestro nivel, brindándole el id del Layout en cuestión
        level.createElements(rootView.findViewById(R.id.level1Main))

        //Llamamos a la función setCurrentLevel de la actividad "Turtle" pasándole el nivel en cuestión
        (activity as Turtle).setCurrentLevel(level)

        return rootView
    }


}
