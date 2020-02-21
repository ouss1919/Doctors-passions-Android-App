package retrofitserver
import classes.Patient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
   @GET("getPatient/{phone}/{password}")
   fun getPatient(@Path("phone") num : String ,@Path("password") pw : String): Call<Patient>
   @GET("getPatients")
   fun getPatients(): Call<List<Patient>>
}
