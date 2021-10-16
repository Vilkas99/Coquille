package com.example.coquille.controllers

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.coquille.R

import com.example.coquille.databinding.ActivitySettingsBinding
import com.example.coquille.models.Settings
import com.example.coquille.models.User
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils

class SettingsActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySettingsBinding
    lateinit var sharedPref: MySharedPreferences
    lateinit var user : User
    lateinit var userSettings : Settings


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        binding = ActivitySettingsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        sharedPref = MySharedPreferences(this)
        user = Utils.getCurrentUser(this)
        userSettings = Utils.getCurrentUser(this).settings

        binding.recommendResting.isChecked = userSettings.breakRecommendations
        binding.recommendResting.setOnCheckedChangeListener { _, isChecked ->
            userSettings.breakRecommendations = isChecked
            saveToSharedPreferences()
        }

        binding.notifyToPlay.isChecked = userSettings.notifications
        binding.notifyToPlay.setOnCheckedChangeListener { _, isChecked ->
            userSettings.notifications = isChecked
            saveToSharedPreferences()
        }


        binding.limitPlayTime.isChecked = userSettings.limitPlayTime
        binding.inputTimeLimit.isEnabled = userSettings.limitPlayTime
        binding.inputTimeLimit.setText(userSettings.timeLimit.toString())
        binding.inputTimeLimit.inputType = if(userSettings.limitPlayTime){InputType.TYPE_CLASS_NUMBER}else{InputType.TYPE_NULL}
        binding.inputTimeLimit.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                if(binding.inputTimeLimit.text.toString().isNotEmpty()){
                    userSettings.timeLimit = binding.inputTimeLimit.text.toString().toInt()
                    binding.inputTimeLimit.hideKeyboard()
                    saveToSharedPreferences()
                }
                else{
                    userSettings.timeLimit = 0
                    saveToSharedPreferences()
                }
                true
            } else {
                false
            }
        }
        binding.limitPlayTime.setOnCheckedChangeListener { _, isChecked ->
            binding.inputTimeLimit.isEnabled = isChecked
            binding.inputTimeLimit.inputType = if(isChecked){InputType.TYPE_CLASS_NUMBER}else{InputType.TYPE_NULL}
            userSettings.limitPlayTime = isChecked
            saveToSharedPreferences()

        }

        binding.sfxOn.isChecked = userSettings.sfx
        binding.sfxOn.setOnCheckedChangeListener { _, isChecked ->
            userSettings.sfx = isChecked
            saveToSharedPreferences()
        }

        binding.musicOn.isChecked = userSettings.music
        binding.musicOn.setOnCheckedChangeListener { _, isChecked ->
            userSettings.music = isChecked
            saveToSharedPreferences()
        }

    }

    private fun saveToSharedPreferences(){
        user.settings = userSettings
        sharedPref.editData(user, "currentUser")
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}