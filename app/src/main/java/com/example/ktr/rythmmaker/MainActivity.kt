package com.example.ktr.rythmmaker

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.VibrationEffect
import android.os.Vibrator
import kotlinx.android.synthetic.main.activity_main.*


var onOrOff = false
var length:Long = 75

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun turnOn(view: View) {

        var waitingTime = beatPerMinute
        var state: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        onOrOff = onOrOff.not()

        if (onOrOff) {
            button.setText( "Turn Off Vibration")

            val time = waitingTime

            val mVibratePattern = longArrayOf(length, length)
            val mAmplitudes = intArrayOf(0, 50)
            state.vibrate(VibrationEffect.createWaveform(mVibratePattern,mAmplitudes,1))
        }
        else
        {
            button.setText("Set The Tone")
            state.cancel()
        }




//            state.vibrate(VibrationEffect.createOneShot(75, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}
