package com.example.coquille.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.coquille.models.User
import com.google.gson.Gson


class MySharedPreferences constructor(context: Context) { //TODO: Corregir todo esta clase :(

    val context = context
    val gson = Gson()


    fun saveData(data: Any?, key: String){
        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = gson.toJson(data)

        Log.d("JSON: ", json)

        editor.apply{
                putString(key, json)
                apply()
        }
    }

    fun editData(data: Any?, key: String){
        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = gson.toJson(data)
        editor.putString(key, json)
        editor.apply()
    }

    fun retrieveData(key: String): String? {
        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val jsonData = sharedPreferences.getString(key, "")
        Log.d("Llegamos el retrieve ", jsonData as String)
        return jsonData
    }
}