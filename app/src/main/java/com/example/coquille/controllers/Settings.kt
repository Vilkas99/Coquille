package com.example.coquille.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.coquille.R

import com.example.coquille.databinding.ActivitySettingsBinding
import com.example.coquille.models.Settings
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils

class Settings : AppCompatActivity(){

    private lateinit var binding: ActivitySettingsBinding
    lateinit var sharedPref: MySharedPreferences
    lateinit var userSettings : Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        binding = ActivitySettingsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        sharedPref = MySharedPreferences(this)
        userSettings = Utils.getCurrentUser(this).settings

        binding.recommendResting.isChecked = userSettings.breakRecommendations
        binding.notifyToPlay.isChecked = userSettings.notifications
        binding.limitPlayTime.isChecked = userSettings.limitPlayTime
        binding.sfxOn.isChecked = userSettings.sfx
        binding.musicOn.isChecked = userSettings.music

    }



}