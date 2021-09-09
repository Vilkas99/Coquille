package com.example.coquille

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class preview_sequence : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_sequence)
    }

    fun routeToSequenceGame(view: View) {
        val intent = Intent(this, Sequence_game::class.java)
        startActivity(intent)
    }

    fun routeToHome(view: View) {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }
}