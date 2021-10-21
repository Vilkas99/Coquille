package com.example.coquille.controllers


import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import com.example.coquille.R
import com.example.coquille.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {

    val register = RegisterFragment()
    val login = LoginFragment()
    val aboutme = AboutMe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragment, login)
                commit()
            }

    }

    fun routeToHome() {
        val player = MediaPlayer.create(this, R.raw.login_sound)
        player.start()
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }

    fun routeAboutMe() {
        val player = MediaPlayer.create(this, R.raw.login_sound)
        player.start()
        val intent = Intent(this, AboutMe::class.java)
        startActivity(intent)
    }


    fun passStage(pass : Boolean){
         var pass = true.apply {
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.fragment, register)
                commit()
            }
        }
    }

    fun passStage2(pass : Boolean){
        var pass = true.apply {
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.fragment, login)
                commit()
            }
        }
    }

    fun passStage3(pass : Boolean){
        var pass = true.apply {
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.fragment, aboutme)
                commit()
            }
        }
    }


}
