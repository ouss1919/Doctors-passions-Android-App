package com.example.tp4v2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.tp4.Team
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/***
class MyWorker(val ctx : Context, val params: WorkerParameters) :ListenableFuture{
    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {

        return future
    }
}***/
/***
class MyWorker(val ctx: Context, val paramters: WorkerParameters): Worker(ctx,paramters) {
    override fun doWork(): Result {
// Implémentation du service
    }***/
class MyWorker(val ctx: Context, val workParamters:
WorkerParameters) : ListenableWorker(ctx, workParamters) {
    lateinit var future: SettableFuture<Result>
    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {
        future = SettableFuture.create<Result>()
        //val team = Room
        addteam(Team(7,"hdgfs","fhsghjf"))
        return future
    }

    private fun addteam(team: Team) {
        val call = RetrofitService.endpoint.insertteam(team)
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>?, response:
                Response<String>?
            ) {
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
            }
        })
    }
}