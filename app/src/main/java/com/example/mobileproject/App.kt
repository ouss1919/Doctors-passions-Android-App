package com.example.tp4

import android.app.Application

class App:Application(){
    override fun onCreate() {
        super.onCreate()
        AppDataBase.RoomService.context = applicationContext }
}