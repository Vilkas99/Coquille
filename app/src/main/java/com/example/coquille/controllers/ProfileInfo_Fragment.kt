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
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils

class ProfileInfo_Fragment : Fragment(R.layout.fragment_profile_info_) {

    //Variable que almacenará la nueva contraseña si esta es cambiada.
    var newPassword = "";

    //Generamos nuestro binding para acceder al XML.
    lateinit var binding : FragmentProfileInfoBinding

    //Creamos las referencias para nuestros EditText
    lateinit var userName : EditText
    lateinit var password : EditText

    //Creamos un objeto de referencias compartidas.
    lateinit var sharedPreferences: MySharedPreferences


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile_info_, container, false)
        val confirm: Button = rootView.findViewById(R.id.confirmButton)

        userName = rootView.findViewById(R.id.input_username)
        password = rootView.findViewById(R.id.input_password)

        sharedPreferences = MySharedPreferences(requireContext())

        //Le establecemos un listener al botón "confirm" en donde al hacerle click llamaremos a la función "updateConfirmation"
        confirm.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                updateConfirmation()
            }
        })

        return rootView
    }


    //Función que se encarga de generar una ventana de confirmación para la actualización de información
    fun updateConfirmation(){
        val builder = AlertDialog.Builder(requireContext()) // Creamos un builder.
        builder.setMessage("¿Estás segur@ de actualizar tus datos?")
            .setCancelable(false)
            .setPositiveButton("Sí"){dialog, id -> // Si el usuario selecciona que sí...
                updateData() //Actualizamos la información
            }
            .setNegativeButton("No"){ dialog, id -> // Si el usuario selecciona que no...
                dialog.dismiss() //Cerramos el diálogo.
            }

        val alert = builder.create()
        alert.show() // Mostramos la alerta.
    }

    fun updateData(){ // Función que se encarga de actualizar la información.
        //Obtenemos los nuevos datos del usuario y la contraseña.
        val newUserName = userName.text.toString()
        val newPassword = password.text.toString()

        //Evaluamos que no estén vacíos.
        val correctData =  newUserName != "" || newPassword != ""
        if (correctData){ // Si no están vacíos...
            //Obtenemos al usuario actual...
            var currentUser = Utils.getCurrentUser(requireContext())


            //Si el nuevo usuario es diferente a ""...
            if (newUserName != ""){
                currentUser.userName = newUserName //Se lo asignamos al usuario actual...
            }

            //Si la contraseña es diferente a ""...
            if (newPassword != ""){
                currentUser.password = newPassword //Se la asignamos al usuario actual.
            }


            //Actualizamos el valor del usuario actual en currentUser,
            sharedPreferences.editData(currentUser, "currentUser", )

            //Le notificamos al usuario que sus datos han sido actualizados...
            Toast.makeText(requireContext(), "Datos actualizados", Toast.LENGTH_SHORT).show()
            //Ejectuamos la función "updateInfo" de la actividad de perfíl.
            (activity as profile).updateInfo();
        }
        else { //En caso de que ambos estén vacíos...
            //Le notificamos al usuario.
            Toast.makeText(requireContext(), "Error: Tanto el nombre de usuario como la contraseña están vacíos", Toast.LENGTH_SHORT).show()
        }


    }
}