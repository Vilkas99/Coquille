package com.example.coquille.controllers

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.coquille.R
import com.example.coquille.databinding.ActivityLoginBinding
import com.example.coquille.models.Collectable
import com.example.coquille.models.Settings
import com.example.coquille.models.User
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Popup


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))

    }

    fun routeToHome(view: View) {

        var sharedPref = MySharedPreferences(this)




        val collectables = MutableList(1){ Collectable("asdasd", 20, "wuajaaaaa") }
        val settings = Settings()
        val user = User("Prueba", "asdawdqw", R.raw.profile_king, 1900, collectables, settings )

        sharedPref.saveData(user, "currentUser")

        val player = MediaPlayer.create(this, R.raw.login_sound)
        player.start()
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }


}
