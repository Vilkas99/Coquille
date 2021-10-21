package com.example.coquille.controllers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.R
import com.example.coquille.controllers.Turtle.Turtle
import com.example.coquille.utils.Utils
import java.time.Duration
import java.time.LocalDateTime


class activity_home : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Utils.createAnimation(findViewById<View>(R.id.racha_icon) as LottieAnimationView, R.raw.fire_animation, 10, -50)
        //TODO: Esto en realidad debería ejecutarse solamente cuando se hace el login. Pendiente hasta que quede bien eso.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notificaciones para jugar"
            val descriptionText = "Notificaciones que te recuerdan que hay que jugar"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(1235322435.toString(), name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


        var userLastPlayedDate =  Utils.getCurrentUserLastPlayedDate(this)
        findViewById<TextView>(R.id.daily_streak_text).text = "Racha de ${Duration.between(userLastPlayedDate, LocalDateTime.now()).toDays()} dias"

    }

    fun routeToConfig(view: View) {
        if(Utils.getCurrentUser(this).settings.sfx) {
            val player = MediaPlayer.create(this, R.raw.settings_sound)
            player.start()
        }
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun routeToProfile(view: View) {
        if(Utils.getCurrentUser(this).settings.sfx){
            val player = MediaPlayer.create(this, R.raw.profile_sound)
            player.start()
        }
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
        if(Utils.getCurrentUser(this).settings.sfx){
            val player = MediaPlayer.create(this, R.raw.sequence_sound)
            player.start()
        }


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



