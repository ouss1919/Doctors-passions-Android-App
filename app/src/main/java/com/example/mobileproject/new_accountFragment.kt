package com.example.mobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import classes.Patient
import kotlinx.android.synthetic.main.fragment_autho.*
import kotlinx.android.synthetic.main.fragment_main.create
import kotlinx.android.synthetic.main.fragment_new_account.*
import kotlinx.android.synthetic.main.fragment_new_account.phone
import kotlin.math.floor


class new_accountFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_new_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val nss:String = nss.text.toString()
        val nom:String = nom.text.toString()
        val prenom:String = prenom.text.toString()
        val adress:String = adress.text.toString()
        val phone:String = phone.text.toString()
        create.setOnClickListener {
            textView4.visibility = TextView.INVISIBLE
            if (checkinputs(nss,nom,prenom,adress,phone)){
                val password = generateRandomPassword()
                val patient = Patient (nss, nom, prenom, adress, phone, password)
                view!!.findNavController().navigate(R.id.action_new_accountFragment2_to_changepasswordFragment,null)
            }
        }
        }

    private fun checkinputs(nss: String, nom: String, prenom: String, adress: String, phone: String): Boolean {
        return if (nss==""||nom==""||prenom==""||adress==""||phone=="") {
            textView4.visibility = TextView.VISIBLE
            false
        } else true
    }
    private fun generateRandomPassword(): String {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var passWord = ""
        for (i in 0..20) {
            passWord += chars[floor(Math.random() * chars.length).toInt()]
        }
        return passWord
    }
}