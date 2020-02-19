package com.example.mobileproject

import androidx.room.PrimaryKey

data class Medication(
    @PrimaryKey
    val id_med : String,
    val name : String
)