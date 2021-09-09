package com.example.coquille

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class activity_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun routeToConfig(view: View) {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    fun routeToProfile(view: View) {
        val intent = Intent(this, profile::class.java)
        startActivity(intent)
    }

    fun routeToSequencePreview(view: View) {
        val intent = Intent(this, preview_sequence::class.java)
        startActivity(intent)
    }

    fun routeToLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}