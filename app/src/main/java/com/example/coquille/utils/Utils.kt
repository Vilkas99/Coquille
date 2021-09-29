package com.example.coquille.utils

import android.content.Context
import android.util.Log
import com.example.coquille.models.User
import com.google.gson.Gson

object Utils {
    @JvmStatic fun getCurrentUser(context: Context) : User{
        Log.d("Llegamos a las utils ", "Rajatelas")
        val gson = Gson()
        val mySharedPreferences = MySharedPreferences(context)

        val currentUserJson = mySharedPreferences.retrieveData("currentUser")
        val currentUser = gson.fromJson(currentUserJson, User::class.java)


        return currentUser
    }




}