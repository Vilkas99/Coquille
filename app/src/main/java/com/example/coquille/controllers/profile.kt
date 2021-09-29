package com.example.coquille.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.coquille.R
import com.example.coquille.databinding.ActivityProfileBinding


class profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    val infoFragment = ProfileInfo_Fragment()
    val achivementFragment = ProfileAchivement_Fragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.profile_fragment, infoFragment)
                commit()
            }
    }



    fun changeToInfo(view: View) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.profile_fragment, infoFragment)
            commit()
        }
    }

    fun changeToAchivement(view: View) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.profile_fragment, achivementFragment)
            commit()
        }
    }

    fun routeToHome(view: View) {
        val intent = Intent(this, activity_home::class.java)
        startActivity(intent)
    }
}