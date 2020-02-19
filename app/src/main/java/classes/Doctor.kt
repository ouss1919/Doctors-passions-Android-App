package com.example.mobileproject

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity =
    Office::class, parentColumns = arrayOf("id_office"),
        childColumns = arrayOf("id_office"),
        onDelete = ForeignKey.CASCADE)
))
data class Doctor (
    @PrimaryKey
    val nssd : String,
    val id_office : String,
    val first_name : String,
    val last_name : String,
    val adress : String,
    val phone : String,
    val password : String,
    val connect : Boolean,
    val specialiy : String,
    val town : String
)