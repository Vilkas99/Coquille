package com.example.coquille.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coquille.utils.MySharedPreferences
import com.example.coquille.utils.Utils

class EndStreakWorkerContext(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {

        val mySharedPreferences = MySharedPreferences(applicationContext)
        val user = Utils.getCurrentUser(applicationContext)
        user.lastPlayedDate = Utils.getCurrentDateInt()
        mySharedPreferences.editData(user, "currentUser")
        return Result.success()
    }

}