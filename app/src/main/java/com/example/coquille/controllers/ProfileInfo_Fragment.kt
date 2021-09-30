package com.example.coquille.controllers

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coquille.R
import com.example.coquille.databinding.FragmentProfileInfoBinding
import com.example.coquille.utils.Utils

class ProfileInfo_Fragment : Fragment(R.layout.fragment_profile_info_) {

    var newPassword = "";

    lateinit var binding : FragmentProfileInfoBinding;
    lateinit var userName : EditText
    lateinit var password : EditText


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile_info_, container, false)
        val location: Button = rootView.findViewById(R.id.updateButton)

        userName = rootView.findViewById(R.id.input_username)
        password = rootView.findViewById(R.id.input_password)

        location.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                updateConfirmation()
            }
        })
        return rootView
    }

    fun updateConfirmation(){
        Log.d("TAG", "Entrando!")
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("¿Estás segur@ de actualizar tus datos?")
            .setCancelable(false)
            .setPositiveButton("Sí"){dialog, id ->
                updateData()
            }
            .setNegativeButton("No"){ dialog, id ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }

    fun updateData(){
        val newUserName = userName.text.toString()
        val newPassword = password.text.toString()
        val correctData =  newUserName != "" || newPassword != ""
        if (correctData){
            var currentUser = Utils.getCurrentUser(requireContext())

            if (newUserName != ""){
                currentUser.userName = newUserName
            }

            if (newPassword != ""){
                currentUser.password = newPassword
            }
            Toast.makeText(requireContext(), "Datos actualizados", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(requireContext(), "Error: Tanto el nombre de usuario como la contraseña están vacíos", Toast.LENGTH_SHORT).show()
        }


    }
}