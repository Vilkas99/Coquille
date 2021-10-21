package com.example.coquille.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.work.*
import com.airbnb.lottie.LottieAnimationView
import com.example.coquille.models.Collectable
import com.example.coquille.models.DateInt
import com.example.coquille.models.User
import com.example.coquille.workers.EndStreakWorkerContext
import com.example.coquille.workers.PlayNotificationWorker
import com.google.gson.Gson
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

object  Utils {

    val collectable1 = Collectable("R.raw.profile_orc", 600, "Caballero de otro mundo")
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

    @JvmStatic fun getCurrentDateInt() : DateInt {

        var now = LocalDateTime.now()
        return DateInt()

    }
    @JvmStatic fun getCurrentUserLastPlayedDate(context: Context) : LocalDateTime{

        var userD = getCurrentUser(context).lastPlayedDate
        return LocalDateTime.of(userD.year, userD.month, userD.day, userD.hour, userD.minute)

    }

    @JvmStatic fun getId(view: View) : String{
        if (view.id == View.NO_ID) return "no-id"
        else return view.resources.getResourceName(view.id)
    }

    @JvmStatic fun sendMessage(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @JvmStatic fun createAnimation(view : LottieAnimationView, animation: Int, repeatCount : Int, padding : Int){
        view.setAnimation(animation)
        view.playAnimation()
        view.setPadding(padding, padding, padding, padding)
        view.repeatCount = repeatCount;
    }

    @JvmStatic fun stopAnimation(view: LottieAnimationView){
        view.repeatCount = 0
        view.setImageDrawable(null)
    }

    @JvmStatic fun setStreakNotification(context: Context){
        val work = OneTimeWorkRequestBuilder<PlayNotificationWorker>()
            .setInitialDelay(20, TimeUnit.HOURS)
            .build()
        val workManager = WorkManager.getInstance(context)
        workManager.enqueue(work)
    }

    @JvmStatic fun setEndStreakWorker(context: Context){

        val work = PeriodicWorkRequestBuilder<EndStreakWorkerContext>(1, TimeUnit.DAYS)
            .setInitialDelay(1, TimeUnit.DAYS)
            .build()
        val workerManager = WorkManager.getInstance(context)
        workerManager.enqueueUniquePeriodicWork("endStreak_coquille", ExistingPeriodicWorkPolicy.REPLACE, work)


    }



}