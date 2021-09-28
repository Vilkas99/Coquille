package com.example.coquille.utils

import android.content.Context
import com.example.coquille.models.User
import com.google.gson.Gson


class SharedPreferences constructor(context: Context) {
    val context = context
    val gson = Gson()

    val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

    fun SaveData(data: Object, key: String){

        val editor = sharedPreferences.edit()
        val json = gson.toJson(data)

        editor.apply{
                putString(key, json)
        }
    }

    fun retrieveData(key: String): String? {
        val jsonData = sharedPreferences.getString(key, "")
        return jsonData
    }
}