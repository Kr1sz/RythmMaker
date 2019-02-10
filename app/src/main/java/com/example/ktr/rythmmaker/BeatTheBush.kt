package com.example.ktr.rythmmaker

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import com.example.ktr.rythmmaker.R.id.beatPerMinute

class BeatTheBush : Service() {


    override fun onBind(intent: Intent?): IBinder? {
        Log.d("K Tag", "Bind")
        return null
    }

    fun vibrate (){
        Log.d("K Tag", "Started")


        var a : Long = 1000

        var state: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val mVibratePattern = longArrayOf(timing, length, timing, length)
        Log.d("K Tag", timing.toString())
        val mAmplitudes = intArrayOf(0, 5,0,5)
        var i : Int = 0

        while ( i<=2) {
            Log.d("K Tag", i.toString())
            state.vibrate(VibrationEffect.createWaveform(mVibratePattern, mAmplitudes, 1))
            Thread.MAX_PRIORITY
            Thread.sleep(1000)
            i++
        }
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        var i : Int = 0
//        while ( i<=2) {
        Thread.sleep(1000)

        vibrate()

//        }
        return Service.START_STICKY

    }

    override fun stopService(name: Intent?): Boolean {
        vibrate()

        return super.stopService(name)
    }
    override fun onDestroy() {
        vibrate()
        Log.d("K tag", "service destroyed")
//        super.onDestroy()
    }
}