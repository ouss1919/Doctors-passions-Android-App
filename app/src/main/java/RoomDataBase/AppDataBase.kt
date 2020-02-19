package com.example.tp4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Player::class, Team::class], version = 1)
abstract class AppDataBase :RoomDatabase() {
    abstract fun getTeamDo(): TeamDAO
    abstract fun getPlayerDo(): PlayerDAO
    object RoomService {
        lateinit var context: Context
        val appDatabase: AppDataBase by lazy {
            Room.databaseBuilder(
                context, AppDataBase::class.java,
                "name"
            )
                .allowMainThreadQueries().build()
        }
    }
}