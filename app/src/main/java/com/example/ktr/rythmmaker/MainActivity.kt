package com.example.ktr.rythmmaker

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager.WakeLock
import android.os.Vibrator
import android.view.View
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

var onOrOff = false
var length:Long = 75

var timing :Long  = 3159


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    public fun turnOn(view: View) {
        onOrOff = onOrOff.not()
        if (onOrOff) {
            button.setText( "Turn Off Vibration")

            Log.d("K Tag", timing.toString())


            timing  = ((60000 / beatPerMinute.text.toString().toDouble()) - length).toLong()

            Log.d("K Tag", timing.toString())
            startService(Intent(this,BeatTheBush::class.java))
//            startForegroundService(Intent(this,BeatTheBush::class.java))
            Log.d("K Tag", "started")
        }
        else
        {
            button.setText("Set The Tone")
            var state: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            state.cancel()
        }
    }
}
