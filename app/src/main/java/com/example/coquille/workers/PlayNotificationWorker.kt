package com.example.coquille.workers

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.example.coquille.R
import com.example.coquille.controllers.MainActivity

class PlayNotificationWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
           flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK}
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        var builder = NotificationCompat.Builder(applicationContext, 1235322435.toString())
            .setContentTitle("¡Ven a jugar y supérate a ti mismo!")
            .setContentText("Practica tu atención y mantén tu racha diaria \uD83D\uDD25")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(applicationContext)){
            notify(23, builder.build())
        }
        return Result.success()

    }



}
