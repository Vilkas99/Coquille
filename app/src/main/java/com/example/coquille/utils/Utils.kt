package com.example.coquille.utils

import android.content.Context
import com.example.coquille.models.User
import com.google.gson.Gson

object Utils {
    val gson = Gson()

    @JvmStatic fun getCurrentUser(context: Context) : User{
        val mySharedPreferences = SharedPreferences(context)
        val currentUserJson = mySharedPreferences.retrieveData("currentUser")
        val currentUser = gson.fromJson(currentUserJson, User::class.java)

        return currentUser
    }




}