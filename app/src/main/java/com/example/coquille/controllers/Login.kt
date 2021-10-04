package com.example.coquille.controllers

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.coquille.R



class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    fun routeToHome(view: View) {
        val player = MediaPlayer.create(this, R.raw.login_sound)
        player.start()
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }


}