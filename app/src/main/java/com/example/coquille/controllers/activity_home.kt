package com.example.coquille.controllers

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.coquille.R

class activity_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun routeToConfig(view: View) {
        val player = MediaPlayer.create(this, R.raw.settings_sound)
        player.start()
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun routeToProfile(view: View) {
        val player = MediaPlayer.create(this, R.raw.profile_sound)
        player.start()
        val intent = Intent(this, profile::class.java)
        startActivity(intent)
    }

    fun routeToSequencePreview(view: View) {
        val player = MediaPlayer.create(this, R.raw.sequence_sound)
        player.start()
        val intent = Intent(this, preview_sequence::class.java)
        startActivity(intent)
    }

    /*fun routeToRepeatedFigures(view: View) {
        val player = MediaPlayer.create(this, R.raw.sequence_sound)
        player.start()
        val intent = Intent(this, repeated_figures::class.java)
        startActivity(intent)
    }*/

    fun routeToLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    fun routeToPreview(view: View){
        val player = MediaPlayer.create(this, R.raw.sequence_sound)
        player.start()
        val intent = Intent(this, PreviewGame::class.java)
        startActivity(intent)
    }

    fun routeToMemoryCardGame(view: View){
        val player = MediaPlayer.create(this, R.raw.sequence_sound)
        player.start()
        val intent = Intent(this,  MemoryGameActivity::class.java)
        startActivity(intent)
    }

}