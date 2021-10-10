package com.example.coquille.controllers

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.coquille.R
import com.example.coquille.databinding.ActivityProfileBinding
import com.example.coquille.models.Collectable
import com.example.coquille.models.Settings
import com.example.coquille.models.User
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils


class profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    val infoFragment = ProfileInfo_Fragment()
    val achivementFragment = ProfileAchivement_Fragment()
    lateinit var currentUser : User
    lateinit var sharedPref: MySharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.profile_fragment, infoFragment)
                commit()
            }

        sharedPref = MySharedPreferences(this)

        val collectables = MutableList(1){Collectable("asdasd", 20, "wuajaaaaa")}
        val settings = Settings()
        val user = User("Prueba", "asdawdqw", R.drawable.ic_profilep_c_king, 1900, collectables, settings )

        sharedPref.saveData(user, "currentUser")

        currentUser = Utils.getCurrentUser(this)
        binding.userName.setText(currentUser.userName)
        binding.userPoints.setText(currentUser.points.toString())
        binding.userPic.setImageResource(currentUser.profilePic)



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

    fun updateInfo(){
        val currentUserUpdated = Utils.getCurrentUser(this)
        binding.userName.setText(currentUserUpdated.userName)
        binding.userPoints.setText(currentUserUpdated.points.toString())
    }

    fun updateAvatar(drawable : Int){
        val currentUserUpdated = Utils.getCurrentUser(this)
        binding.userPic.setImageResource(drawable)
        currentUserUpdated.profilePic = drawable
        sharedPref.editData(currentUserUpdated, "currentUser")
        updateInfo()
    }
}