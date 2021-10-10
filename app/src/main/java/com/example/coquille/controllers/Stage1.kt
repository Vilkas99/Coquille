package com.example.coquille.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.coquille.R


class stage1 : Fragment() {

    lateinit var image1: ImageButton
    lateinit var image2: ImageButton
    lateinit var image3: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sequence_stage, container, false)

        image1 = rootView.findViewById(R.id.imageSpider)
        image2 = rootView.findViewById(R.id.imagePotion1)
        image3 = rootView.findViewById(R.id.imagePotion2)

        image1.setOnClickListener() {
            image1.setImageResource(R.drawable.ic_spider)
        }

        return rootView
    }

}