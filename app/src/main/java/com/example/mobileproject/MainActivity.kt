package com.example.mobileproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
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
        val num:String = phone.text.toString()
        val pw:String = password.text.toString()
        Connect.setOnClickListener {
            if (checkinputs(num,pw)) {
                val patient : Patient? = verify_identity (num, pw)
                if (patient!=null) Toast.makeText(this, "Sucsses", Toast.LENGTH_SHORT).show()
                else Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verify_identity(num: String, pw: String): Patient? {
        var patient : Patient? = null
    val call = RetrofitService.endpoint.getPatient(num, pw)
        call.enqueue(object: Callback<Patient> {
            override fun onResponse(call: Call<Patient>?, response:
            Response<Patient>?) {
                 patient = response!!.body()!!
                }
            override fun onFailure(call: Call<Patient>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, t!!.message, Toast.LENGTH_SHORT).show()
            }
        })
        return patient
    }

    private fun checkinputs(num: String, pw: String): Boolean {
        if (num == null|| pw == null) {
            if (num == null) {
                textView.visibility = EditText.VISIBLE
            }
            if (pw == null) {
                textView2.visibility = EditText.VISIBLE
            }
            return false
        }else
            return true
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<Patient>) {

}
