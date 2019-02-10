package com.example.ktr.rythmmaker

import android.content.Context
import android.icu.util.UniversalTimeScale.toLong
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round


var onOrOff = false
var length:Long = 75

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun turnOn(view: View) {

        var freq= beatPerMinute.text
        var a :Long  = ((60000 / freq.toString().toDouble()) - length).toLong()
        Log.d("K Tag", a.toString())
        var state: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        onOrOff = onOrOff.not()

        if (onOrOff) {
            button.setText( "Turn Off Vibration")


            val mVibratePattern = longArrayOf(a, length, a, length)
//            val mAmplitudes = intArrayOf(0, 50)
            state.vibrate(VibrationEffect.createWaveform(mVibratePattern,1))
        }
        else
        {
            button.setText("Set The Tone")
            state.cancel()
        }




//            state.vibrate(VibrationEffect.createOneShot(75, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}
