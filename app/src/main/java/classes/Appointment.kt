package classes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity
data class Appointment (
    @PrimaryKey
    val id_appointment : String,
    val id_agenda : String,
    val date : Date ,
    val time : Time ,
    val nssd : String,
    val nssp : String
)