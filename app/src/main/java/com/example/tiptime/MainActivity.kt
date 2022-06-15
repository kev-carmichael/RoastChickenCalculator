package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }

    }

    fun calculateTip(){
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDouble()
        var tip : Double = cost*45
        var timeHrs: Int = 0
        var timeMins: Int = 0
        var temp : Int = 200
        val rest : Int =15
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            temp = temp-20
        }

        if(tip>60){
            timeHrs = tip.toInt()/60
            timeMins = tip.toInt()%60
        } else{
            timeHrs = 0
            timeMins = tip.toInt()
        }

        binding.tipResult.text =  getString(R.string.tip_amount, timeHrs.toString())+" "+getString(R.string.time_mins, timeMins.toString())
        //binding.tipResult1.text = getString(R.string.time_mins, timeMins.toString())
        binding.tempResult.text = getString(R.string.temp_amount, temp.toString())
        binding.restResult.text = getString(R.string.rest_amount, rest.toString())
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}