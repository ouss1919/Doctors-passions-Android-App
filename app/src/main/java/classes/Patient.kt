package classes
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Patient")
data class Patient (
    @PrimaryKey
    val nssp : String,
    val first_name : String,
    val last_name : String,
    val adress : String,
    val phone : String,
    val password : String
)