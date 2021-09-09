package com.example.coquille

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class profile : AppCompatActivity() {

    val infoFragment = ProfileInfo_Fragment()
    val achivementFragment = ProfileAchivement_Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
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
}