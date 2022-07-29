package com.example.demo.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.demo.sensors.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
     private val stepSensor: MeasurableSensor
) :ViewModel(){
    var stepCount = mutableStateOf<Float>(0f)

    init {
        stepSensor.startListening()
        stepSensor.setOnSensorValuesChangedListener { values->
            Log.d("Step Sensor","value is ${values}")
        }
    }

}