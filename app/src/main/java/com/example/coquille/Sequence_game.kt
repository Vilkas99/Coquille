package com.example.coquille

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Sequence_game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequence_game)
    }

    fun routeToPreviewSequence(view: View) {
        val intent = Intent(this, preview_sequence::class.java)
        startActivity(intent)
    }
}