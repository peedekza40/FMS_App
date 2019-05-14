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
import org.json.JSONArray
import com.android.volley.DefaultRetryPolicy




class Income(
    val context: Context,
    val cacheDir: File): Conifg_service(context, cacheDir){

    val TAG = "Service Inc"
    init {
    }

    fun get_all(callback: VolleyCallback){
        url = ip
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
        stringRequest.tag = TAG
        // Add the request to the RequestQueue.
        requestQueue?.add(stringRequest)
    }

    fun get_last_inc_code(callback: VolleyCallback){
        url = ip
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
        stringRequest.tag = TAG
        // Add the request to the RequestQueue.
        requestQueue?.add(stringRequest)
    }

    fun insert(jsonBody: JSONObject){
        url = ip
        url += "/insert_income"
        // Request a string response from the provided URL.
        val objectRequest = JsonObjectRequest(
            Request.Method.POST, url,jsonBody,
            Response.Listener<JSONObject> {response ->

            },
            Response.ErrorListener {
                    response-> Toast.makeText(context, "${response}", Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue?.add(objectRequest)
    }

    fun cancleRequest(){
        requestQueue?.cancelAll(TAG)
    }
}