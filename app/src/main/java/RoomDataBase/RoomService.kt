

import androidx.room.Room
import android.content.Context
import com.example.tp4.AppDataBase

object RoomService {


    lateinit var context: Context

    val appDataBase: AppDataBase by lazy {
        Room.databaseBuilder(context,AppDataBase::class.java,"projet").allowMainThreadQueries().build()
    }
}