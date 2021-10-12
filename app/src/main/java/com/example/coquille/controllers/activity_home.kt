package com.example.coquille.controllers

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.R
import com.example.coquille.utils.Utils


class activity_home : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Utils.createAnimation(findViewById<View>(R.id.racha_icon) as LottieAnimationView, R.raw.fire_animation, 10, -50)

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

    fun routeToLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    fun routeToPreview(view: View ){
        val nameCard = view.resources.getResourceName(view.id)
        val player = MediaPlayer.create(this, R.raw.sequence_sound)
        player.start()

        when(nameCard){
            "com.example.coquille:id/sequence_button" -> {
                val intent = Intent(this, PreviewGame::class.java)
                val nameGame :String = "sequence"
                val b : Bundle = Bundle()
                b.putString("indexArray", nameGame)
                intent.putExtras(b)
                startActivity(intent)
            }
            "com.example.coquille:id/memory_button" -> {
                val intent = Intent(this, PreviewGame::class.java)
                val nameGame :String = "memory"
                val b : Bundle = Bundle()
                b.putString("indexArray", nameGame)
                intent.putExtras(b)
                startActivity(intent)
            }
            "com.example.coquille:id/tortuguita_button" -> {
                val intent = Intent(this, PreviewGame::class.java)
                val nameGame :String = "tortuga"
                val b : Bundle = Bundle()
                b.putString("indexArray", nameGame)
                intent.putExtras(b)
                startActivity(intent)
            }
            "com.example.coquille:id/book_button" -> {
                val intent = Intent(this, PreviewGame::class.java)
                val nameGame :String = "book"
                val b : Bundle = Bundle()
                b.putString("indexArray", nameGame)
                intent.putExtras(b)
                startActivity(intent)
            }
            "com.example.coquille:id/colorful_button" -> {
                val intent = Intent(this, PreviewGame::class.java)
                val nameGame :String = "colorful"
                val b : Bundle = Bundle()
                b.putString("indexArray", nameGame)
                intent.putExtras(b)
                startActivity(intent)
            }
            else -> println("Botón inexistente")
        }


    }

    fun routeToMemoryCardGame(view: View){
        val player = MediaPlayer.create(this, R.raw.sequence_sound)
        player.start()
        val intent = Intent(this,  MemoryGameActivity::class.java)
        startActivity(intent)
    }

    fun routeToTortuga(view: View){
        val player = MediaPlayer.create(this, R.raw.sequence_sound)
        player.start()
        val intent = Intent(this, Turtle::class.java)
        startActivity(intent)
    }

}



