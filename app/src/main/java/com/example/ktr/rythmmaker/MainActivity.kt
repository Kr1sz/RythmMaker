package com.example.ktr.rythmmaker

import android.app.AlarmManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.AsyncTask
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

var length:Long = 75
var timing :Long  = 3159


class MainActivity : AppCompatActivity() {

    var myService: BeatTheBush? = null
    var isBound = false


    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(
            className: ComponentName,
            service: IBinder
        ) {
            val binder = service as BeatTheBush.MyLocalBinder
            myService = binder.getService()
            isBound = true

            getBackgroundNotification(applicationContext, myService).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        override fun onServiceDisconnected(name: ComponentName) {

            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

    }

    fun turnOn(view: View) {
        timing = ((60000 / beatPerMinute.text.toString().toDouble()) - length).toLong()
        val serviceClass = BeatTheBush::class.java
        val serviceIntent = Intent(applicationContext, serviceClass)

        startService(serviceIntent)
        bindService(serviceIntent, myConnection, Context.BIND_AUTO_CREATE)

    }
}