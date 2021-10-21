package com.example.coquille.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.example.coquille.R

class AboutMe : Fragment() {

    var bool = false
    lateinit var backButton: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_about_me, container, false)

        backButton = rootView.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            (activity as Login).passStage2(true)
        }

        return rootView
    }
}