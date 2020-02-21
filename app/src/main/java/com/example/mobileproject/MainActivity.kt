package com.example.mobileproject
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import classes.Patient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Connect.setOnClickListener {
            val num:String = phone.text.toString()
            val pw:String = password.text.toString()
            textView3.visibility = TextView.INVISIBLE
            if (checkinputs(num,pw)) {
                verify_identity (num, pw)
            }
        }
    }
    private fun verify_identity(num: String, pw: String) {
    val call = RetrofitService.endpoint.getPatient(num, pw)
        call.enqueue(object: Callback<Patient> {
            override fun onResponse(call: Call<Patient>?, response:
            Response<Patient>?) {
                Toast.makeText(this@MainActivity, response?.body()!!.first_name+"sucsess", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<Patient>, t: Throwable?) {
                textView3.visibility = TextView.VISIBLE
            }
        })
    }

    private fun checkinputs(num: String, pw: String): Boolean {
        textView.visibility = EditText.INVISIBLE
        textView2.visibility = EditText.INVISIBLE
        return if (num == "" || pw == "") {
            if (num == "") {
                textView.visibility = EditText.VISIBLE
            }
            if (pw == "") {
                textView2.visibility = EditText.VISIBLE
            }
            false
        }else
            true
    }
}
