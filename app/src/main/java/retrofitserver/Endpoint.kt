package retrofitserver
import classes.Patient
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {

   @GET("getPatient")
   fun getPatient(@Path("phone") num : String,@Path("password") pw : String): retrofit2.Call<Patient?>
}
