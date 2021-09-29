package com.example.coquille.controllers

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coquille.R
import com.example.coquille.databinding.FragmentProfileInfoBinding
import com.example.coquille.utils.Utils

class ProfileInfo_Fragment : Fragment(R.layout.fragment_profile_info_) {

    val newUsername = R.id.input_useranme
    val newPassword = R.id.input_password


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile_info_, container, false)
        val location: Button = rootView.findViewById(R.id.updateButton)
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

        val correctData = newUsername != null || newPassword != null

        if (correctData){
            var currentUser = Utils.getCurrentUser(requireContext())

            if (newUsername.toString() != null){
                currentUser.userName = newUsername.toString()
            }

            if (newPassword.toString() != null){
                currentUser.password = newPassword.toString()
            }
            Toast.makeText(requireContext(), "Datos actualizados", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(requireContext(), "Error: Tanto el nombre de usuario como la contraseña están vacíos", Toast.LENGTH_SHORT).show()
        }


    }
}