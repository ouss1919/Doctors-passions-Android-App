import androidx.room.*
import classes.Patient

@Dao
interface PatientDAO {
    @Insert
    fun insertpatient(patient: Patient)

    @Update
    fun updatepatient(patient:Patient)

    @Delete
    fun deletepatient(patient:Patient)

  //  @Query("select * from patient")
   // fun allpatients():List<Patient>

}