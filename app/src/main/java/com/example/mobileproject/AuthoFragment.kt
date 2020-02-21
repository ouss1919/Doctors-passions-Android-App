package com.example.mobileproject

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import classes.Patient
import kotlinx.android.synthetic.main.fragment_autho.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_autho, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Connect.setOnClickListener {
            val num:String = phone.text.toString()
            val pw:String = password.text.toString()
            textView3.visibility = TextView.INVISIBLE
            if (checkinputs(num,pw)) {
                verify_identity (num, pw)
            }
        }
        newaccount.setOnClickListener {
            view!!.findNavController().navigate(R.id.action_authoFragment_to_new_accountFragment2,null)
        }
    }
    private fun verify_identity(num: String, pw: String) {
        val call = RetrofitService.endpoint.getPatient(num, pw)
        call.enqueue(object: Callback<Patient> {
            override fun onResponse(call: Call<Patient>?, response:
            Response<Patient>?) {
                Toast.makeText(activity, response?.body()!!.first_name+"sucsess", Toast.LENGTH_SHORT).show()
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
