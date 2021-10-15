package com.example.coquille

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.coquille.controllers.Login


class RegisterFragment : Fragment() {
    lateinit var buttonLogin : TextView
    var bool = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_register, container, false)

        buttonLogin = rootView.findViewById(R.id.iniciaSesion)
        buttonLogin.setOnClickListener{
            (activity as Login).passStage2(bool)
        }

        return rootView
    }
}