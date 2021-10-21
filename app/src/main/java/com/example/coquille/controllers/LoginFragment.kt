package com.example.coquille.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.coquille.R
import com.example.coquille.models.User
import com.example.coquille.models.Users
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.lang.reflect.Type


class LoginFragment : Fragment() {
    lateinit var buttonRegister : TextView
    lateinit var buttonLogin : Button
    lateinit var aboutMe: ImageButton
    lateinit var nameField : TextInputEditText
    lateinit var passwordField : TextInputEditText
    var bool = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        buttonRegister = rootView.findViewById(R.id.Registrarse)
        buttonRegister.setOnClickListener {
            (activity as Login).passStage(true)
        }

        buttonLogin = rootView.findViewById(R.id.confirmButton)
        buttonLogin.setOnClickListener {
            login()
        }

        aboutMe = rootView.findViewById(R.id.aboutMe)
        aboutMe.setOnClickListener {
            (activity as Login).passStage3(true)
        }

        nameField = rootView.findViewById(R.id.input_username)
        passwordField = rootView.findViewById(R.id.input_password)

        return rootView
    }

    fun validateCredentials() : Boolean{
        val mySharedPreferences = MySharedPreferences(requireContext())
        val users = mySharedPreferences.retrieveData("users")

        val myName = nameField.text.toString()
        val mypassword = passwordField.text.toString()

        if (myName == "" || mypassword == ""){
            Utils.sendMessage("No puede haber datos vacíos", requireContext())
            return false
        }

        if (users != ""){
            val gson = Gson()
            val usersData = gson.fromJson(users, Users::class.java)

            usersData.users.forEach { user ->
                if (user.userName == myName  && user.password == mypassword){
                    mySharedPreferences.editData(user, "currentUser", )
                    return true
                }
            }

            Utils.sendMessage("Las credenciales son erróneas.", requireContext())

        } else {
            Utils.sendMessage("No hay jugadores registrados.", requireContext())
        }

        return false
    }


    fun login(){
        val validation = validateCredentials()
        if (validation){
            (activity as Login).routeToHome()
        }
    }

}