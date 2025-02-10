package com.tiranga.acharyaattendence

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    companion object {
        const val TAG = "LivenessApp"
    }
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}