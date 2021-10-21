package com.example.coquille.controllers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.coquille.R
import com.example.coquille.models.Collectable
import com.example.coquille.models.Settings
import com.example.coquille.models.User
import com.example.coquille.models.Users
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson


class RegisterFragment : Fragment() {
    lateinit var buttonLogin : TextView
    lateinit var buttonRegister : Button
    lateinit var userName : TextInputEditText
    lateinit var password : TextInputEditText
    lateinit var passwordVerify : TextInputEditText

    var bool = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_register, container, false)

        buttonLogin = rootView.findViewById(R.id.iniciaSesion)
        buttonLogin.setOnClickListener{
            (activity as Login).passStage2(bool)
        }

        userName = rootView.findViewById(R.id.input_username)
        password = rootView.findViewById(R.id.input_password)
        passwordVerify = rootView.findViewById(R.id.input_password_verify)

        buttonRegister = rootView.findViewById(R.id.confirmButton)
        buttonRegister.setOnClickListener {
            register()
        }

        return rootView
    }

    fun validateRegister() : Boolean{
        val userNameText = userName.text.toString()
        val passwordText = password.text.toString()
        val passwordVerifyText = passwordVerify.text.toString()

        if(userNameText != "" && passwordText != "" &&  passwordVerifyText != ""){
            if (passwordText == passwordVerifyText)
                return true
            else
                Utils.sendMessage("Las contraseñas no coinciden", requireContext())
        } else{
            Utils.sendMessage("Debes rellenar todo el formulario", requireContext())
        }
        return false
    }

    fun register(){
        if(validateRegister()){
            val settings = Settings()
            val collectables = MutableList(100){ Collectable("asdasd", 20, "wuajaaaaa") }
            val newUser = User(userName.text.toString(), password.text.toString(), R.raw.profile_king, 0, collectables, settings)

            val mySharedPreferences = MySharedPreferences(requireContext())
            val users = mySharedPreferences.retrieveData("users")

            if (users != ""){
                val gson = Gson()
                val usersData = gson.fromJson(users, Users::class.java)

                usersData.users.forEach { user ->
                    if(user.userName == newUser.userName){
                        Utils.sendMessage("Ese usuario ya existe, por favor crea uno distinto", requireContext())
                        return
                    }
                }

                usersData.users.add(newUser)
                mySharedPreferences.editData(usersData,"users" )
            } else {
                val list = MutableList(1){newUser }
                val newUsersData = Users(list)
                mySharedPreferences.saveData(newUsersData, "users")
            }

            mySharedPreferences.editData(newUser, "currentUser", )


            (activity as Login).routeToHome()
        }
    }
}