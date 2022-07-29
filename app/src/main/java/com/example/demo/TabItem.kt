package com.example.demo

import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable ()-> Unit

sealed class TabItem(var title:String ,var screen:ComposableFun){
    @OptIn(ExperimentalMaterial3Api::class)
    object Counter: TabItem("Pedometer", screen = { CounterScreen()})
    @OptIn(ExperimentalMaterial3Api::class)
    object Reminder: TabItem("Reminder", screen = { ReminderScreen() })
    @OptIn(ExperimentalMaterial3Api::class)
    object Exercise: TabItem("Exercises", screen = { ExerciseScreen() })

}