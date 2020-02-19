package com.example.mobileproject

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*


@Entity(foreignKeys = arrayOf(
    ForeignKey(entity =
    Treating_doctor::class, parentColumns = arrayOf("nssd"),
        childColumns = arrayOf("nssd"),
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity =
    Treating_doctor::class, parentColumns = arrayOf("nssp"),
        childColumns = arrayOf("nssp"),
        onDelete = ForeignKey.CASCADE),
    ForeignKey(entity =
    Agenda::class, parentColumns = arrayOf("id_agenda"),
    childColumns = arrayOf("id_agenda"),
    onDelete = ForeignKey.CASCADE)
))

data class Appointment (
    @PrimaryKey
    val id_appointment : String,
    val id_agenda : String,
    val date : Date ,
    val time : Time ,
    val nssd : String,
    val nssp : String
)