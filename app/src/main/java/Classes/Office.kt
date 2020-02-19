package com.example.mobileproject

import androidx.room.PrimaryKey

data class Office (
    @PrimaryKey
    val id_office : String ,
    val adress : String,
    val openning_closing_time : String ,
    val phone : String
)