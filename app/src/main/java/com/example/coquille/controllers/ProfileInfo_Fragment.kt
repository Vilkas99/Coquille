package com.example.coquille.controllers

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coquille.R
import com.example.coquille.utils.Utils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileInfo_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileInfo_Fragment : Fragment(R.layout.fragment_profile_info_) {

    val newUsername = R.id.input_useranme
    val newPassword = R.id.input_password

    val updateButton = findViewById(R.id.updateButton) as Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateButton.setOnClickListener { view ->
            updateConfirmation(view)
        }
    }



    fun updateConfirmation(view: View   ){
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