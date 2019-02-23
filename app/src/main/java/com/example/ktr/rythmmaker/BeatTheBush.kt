package com.example.ktr.rythmmaker

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log

class BeatTheBush : Service() {

    private val myBinder = MyLocalBinder()

    override fun onBind(intent: Intent): IBinder? {
        return myBinder
    }

    fun vibrate(): String {
        Log.d("K Tag", "Started")
        val state: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val mVibratePattern = longArrayOf(timing, length, timing, length)
        Log.d("K Tag", timing.toString())
        val mAmplitudes = intArrayOf(0, 5,0,5)
        var i = 0

        while (i <= 1) {
            Log.d("K Tag", i.toString())
            state.vibrate(VibrationEffect.createWaveform(mVibratePattern, mAmplitudes, 1))
            Thread.MAX_PRIORITY
            Thread.sleep(1000)
            i++
        }
        return "Buzz On"
    }

    inner class MyLocalBinder : Binder() {
        fun getService(): BeatTheBush {
            return this@BeatTheBush
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Thread.sleep(1000)
        vibrate()
        return Service.START_STICKY
    }

    override fun onDestroy() {
        val state: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        state.cancel()
        Thread.sleep(100)
        state.cancel()
        super.onDestroy()
        stopForeground(true)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {

        super.onTaskRemoved(rootIntent)
        stopSelf()
    }
}