package Classes
import androidx.room.PrimaryKey


data class Patient (
    @PrimaryKey
    val nssp : String,
    val first_name : String,
    val last_name : String,
    val adress : String,
    val phone : String,
    val password : String,
    val connect : Boolean
)