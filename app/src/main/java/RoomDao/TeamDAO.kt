
import androidx.room.*
import com.example.tp4.Team

@Dao
interface TeamDAO {

    @Insert
    fun insertteam(team: Team)

    @Update
    fun updateteam(team:Team)

    @Delete
    fun deleteteam(team:Team)

    @Query("select * from Team")
    fun allTeams():List<Team>

}