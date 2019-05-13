package com.example.fms_app.Service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.io.File

class Bank(
    val context: Context,
    val cacheDir: File): Conifg_service(context, cacheDir) {

    fun get_bank(callback: VolleyCallback){
        url += "/get_bank"
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
        stringRequest.tag = "Service FMS"
        // Add the request to the RequestQueue.
        requestQueue?.add(stringRequest)
    }
}