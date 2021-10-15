package com.example.coquille

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.coquille.controllers.Login

class LoginFragment : Fragment() {
    lateinit var buttonRegister : TextView
    var bool = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        buttonRegister = rootView.findViewById(R.id.Registrarse)
        buttonRegister.setOnClickListener{
            (activity as Login).passStage(bool)
        }

        return rootView
    }


}