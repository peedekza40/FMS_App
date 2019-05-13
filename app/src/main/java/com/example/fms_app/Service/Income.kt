package com.example.fms_app.Service

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import java.io.File
import kotlin.collections.ArrayList
import org.json.JSONObject
import com.example.fms_app.Service.Conifg_service



class Income(
    val context: Context,
    val cacheDir: File): Conifg_service(context, cacheDir){

    init {
    }

    fun get_all(callback: VolleyCallback){
        url += "/get_all_income"
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                //val accounting = JSONArray(response)
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

    fun get_last_inc_code(callback: VolleyCallback){
        url += "/get_last_inc_code"
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

    fun insert(callback: VolleyCallback){
        url += "/insert_income"
        val jsonBody = JSONObject()
        //jsonBody.put("")
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                //val accounting = JSONArray(response)
                callback.onSuccess(response)
            },
            Response.ErrorListener {
                    response-> Toast.makeText(context, "${response}", Toast.LENGTH_SHORT).show()
            }
        )
    }


}