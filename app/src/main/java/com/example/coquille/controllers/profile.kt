package com.example.coquille.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.coquille.R
import com.example.coquille.databinding.ActivityProfileBinding
import com.example.coquille.models.Collectable
import com.example.coquille.models.Settings
import com.example.coquille.models.User
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils
import com.example.coquille.utils.HeartSystem

class profile : AppCompatActivity() {

    //Binding para poder accesar a los elementos del XML.
    private lateinit var binding: ActivityProfileBinding

    //Referencias a los fragmentos
    val infoFragment = ProfileInfo_Fragment()
    val achivementFragment = ProfileAchivement_Fragment()

    lateinit var currentUser : User
    lateinit var sharedPref: MySharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Realizamos el binding de la actividad.
        binding = ActivityProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //Establecemos que el fragmento que ocupará el espacio inicial será el de perfil.
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.profile_fragment, infoFragment)
                commit()
            }

        //Creamos nuestas "sharedPreferences"
        sharedPref = MySharedPreferences(this)

        //Obtenemos al usuario actual
        currentUser = Utils.getCurrentUser(this)
        //Accedemos al texto XML "username" y le colocamos el nombre del usuario.
        binding.userName.setText(currentUser.userName)
        //Caso análogo para userPoints.
        binding.userPoints.setText(currentUser.points.toString())


        //Llamamos a Utils y ejecutamos la función "createAnimation"
        Utils.createAnimation(binding.userPic, currentUser.profilePic, 10, -5)

    }


    //Función que cambia el fragmento al de perfíl.
    fun changeToInfo(view: View) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.profile_fragment, infoFragment)
            commit()
        }
    }

    //Función que cambia el fragmento al de logros.
    fun changeToAchivement(view: View) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.profile_fragment, achivementFragment)
            commit()
        }
    }

    //Función que traslada al usuario hacia la vista de "Home"
    fun routeToHome(view: View) {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }

    //Función que se encarga de actualizar los objetos visuales del perfíl
    fun updateInfo(){
        val currentUserUpdated = Utils.getCurrentUser(this)
        binding.userName.setText(currentUserUpdated.userName)
        binding.userPoints.setText(currentUserUpdated.points.toString())
    }

    //Función que se encarga de actualizar el avatar del usuario a través de una animación que se le brinde
    fun updateAvatar(animation : Int){
        val currentUserUpdated = Utils.getCurrentUser(this)
        Utils.createAnimation(binding.userPic, animation, 40, -5)
        currentUserUpdated.profilePic = animation
        sharedPref.editData(currentUserUpdated, "currentUser")
        updateInfo()
    }
}