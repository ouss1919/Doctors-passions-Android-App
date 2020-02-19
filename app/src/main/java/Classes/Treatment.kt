package com.example.mobileproject

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Treatment (
    @PrimaryKey
    val id_treatment : String,
    val nssd : String,
    val nssp : String,
    val duration : String
)