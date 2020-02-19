
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import classes.Patient

@Database(entities = [Patient::class], version = 1)
abstract class AppDataBase :RoomDatabase() {
      abstract fun getPatient(): PatientDAO
    //abstract fun getPlayerDo(): PlayerDAO
}