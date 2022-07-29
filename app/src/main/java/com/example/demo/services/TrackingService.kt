package com.example.demo.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.example.demo.MainActivity
import com.example.demo.R
import com.example.demo.others.Constants.ACTION_PAUSE_SERVICE
import com.example.demo.others.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.demo.others.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.demo.others.Constants.ACTION_STOP_SERVICE
import com.example.demo.others.Constants.NOTIFICATION_CHANNEL_ID
import com.example.demo.others.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.demo.others.Constants.NOTIFICATION_ID
import timber.log.Timber

class TrackingService: Service() {

    var isFirstRun = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    if(isFirstRun) {
                        Log.i("onStart","ACTION_START_OR_RESUME_SERVICE")
                        startForegroundService()
                        Log.i("onFore","ACTION_START_OR_RESUME_SERVICE")
                        isFirstRun = false
                    } else {
                        Log.i("onStart","Start")
                        Timber.d("Resuming service...")
                    }
                }
                ACTION_PAUSE_SERVICE -> {
                    Timber.d("Paused service")
                }
                ACTION_STOP_SERVICE -> {

                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun startForegroundService() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.
            Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Step Counter App")
            .setContentText("0")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(
            this,
            MainActivity::class.java)
            .also {
            it.action = ACTION_SHOW_TRACKING_FRAGMENT },
            FLAG_UPDATE_CURRENT
             )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

}