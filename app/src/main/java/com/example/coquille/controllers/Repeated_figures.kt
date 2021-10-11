package com.example.coquille.controllers

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.coquille.R
import com.example.coquille.databinding.ActivityRepeatedFiguresBinding

class Repeated_figures : AppCompatActivity() {

    private lateinit var binding: ActivityRepeatedFiguresBinding
    val repetFragment = repeated_stage1()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repeated_figures)
        binding = ActivityRepeatedFiguresBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragment_repeated, repetFragment)
                commit()
            }
    }
}