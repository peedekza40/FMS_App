package com.example.fms_app.Service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.io.File
import com.example.fms_app.Service.Conifg_service

class Bank_account (
    val context: Context,
    val cacheDir: File) : Conifg_service(context, cacheDir){

    val TAG = "Service Bac"
    fun get_bankAccount(callback: VolleyCallback){
        url += "Bankaccount_service/get_bankAccount"
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                callback.onSuccess(response)
            },
            Response.ErrorListener {
                    response-> Log.d("Service error",response.toString())
            }
        )
        stringRequest.tag = TAG
        // Add the request to the RequestQueue.
        requestQueue?.add(stringRequest)
    }

    fun cancleRequest(){
        requestQueue?.cancelAll(TAG)
    }
}