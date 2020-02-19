package com.example.mobileproject

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity =
    Doctor::class, parentColumns = arrayOf("nssd"),
        childColumns = arrayOf("nssd"),
        onDelete = ForeignKey.CASCADE)
))
data class Agenda (
    @PrimaryKey
    val id_agenda :String,
    @PrimaryKey
    val nssd  : String,
    val max_rdv : Int

)