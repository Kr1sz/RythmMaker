package com.example.ktr.rythmmaker

//import com.example.ktr.rythmmaker.R.id.imageView
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.AsyncTask
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

var length:Long = 75
var timing :Long  = 3159
var switch: Boolean = true

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
        Log.d("Picture", "Pic")
//        imageView.setImageDrawable(getDrawable(R.mipmap.eggbckg))
    }

    fun turnOn(view: View) {
        val serviceClass = BeatTheBush::class.java
        val serviceIntent = Intent(applicationContext, serviceClass)
        Log.d("K Tag", switch.toString())
        if (switch) {
            timing = ((60000 / beatPerMinute.text.toString().toDouble()) - length).toLong()
            switch = false


            startService(serviceIntent)
            bindService(serviceIntent, myConnection, Context.BIND_AUTO_CREATE)
            button.text = "Stop the beat"

        } else {
            button.text = "Set the beat"
            try {
                unbindService(myConnection)
                stopService(serviceIntent)

            } catch (e: IllegalArgumentException) {
                Log.w("MainActivity", "Error Unbinding Service.")
            }
            super.onDestroy()
            switch = true

        }

    }
}