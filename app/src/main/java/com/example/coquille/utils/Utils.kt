package com.example.coquille.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.coquille.models.Collectable
import com.example.coquille.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Utils {

    val collectable1 = Collectable("ic_profile_pic_orc", 600, "Caballero de otro mundo")
    val collectable2 = Collectable("ic_profile_pic_mage", 900, "Hechicera del saber" )

    val collectables = listOf(collectable1, collectable2)

    @JvmStatic fun getCurrentUser(context: Context) : User{
        val gson = Gson()
        val mySharedPreferences = MySharedPreferences(context)

        val currentUserJson = mySharedPreferences.retrieveData("currentUser")
        val currentUser = gson.fromJson(currentUserJson, User::class.java)


        return currentUser
    }


    @JvmStatic fun getCurrentCollectables(context: Context) : List<Collectable>{
        return collectables
    }

    @JvmStatic fun getCollectable(context: Context, title: String) : Collectable{

        val dummy = Collectable("", 0, "Dummy")

        collectables.forEach { collectable ->
            if(collectable.title == title){
                return collectable
            }
        }

        return dummy
    }

    @JvmStatic fun getId(view: View) : String{
        if (view.id == View.NO_ID) return "no-id"
        else return view.resources.getResourceName(view.id)
    }

    @JvmStatic fun sendMessage(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}