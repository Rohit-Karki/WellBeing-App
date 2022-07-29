package com.example.demo.di

import android.app.Application
import com.example.demo.sensors.MeasurableSensor
import com.example.demo.sensors.Sensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {
    @Provides
    @Singleton
    fun provideStepSensor (app:Application): MeasurableSensor {
        return Sensor(context = app)
    }
}