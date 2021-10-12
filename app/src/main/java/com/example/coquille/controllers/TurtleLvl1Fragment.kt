package com.example.coquille.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import androidx.core.view.forEach
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.R
import com.example.coquille.models.games.Turtle.Level
import com.example.coquille.models.games.Turtle.Position
import com.example.coquille.utils.Utils

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

        val level = Level()
        level.createElements(rootView.findViewById(R.id.level1Main))

        (activity as Turtle).setLevel(level)

        return rootView
    }


}
