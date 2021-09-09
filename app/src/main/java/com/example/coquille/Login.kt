package com.example.coquille

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun routeToHome(view: View) {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }
}