package com.example.fms_app.Service

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.io.File

class Bank(
    val context: Context,
    val cacheDir: File): Conifg_service(context, cacheDir) {

    fun get_bank(callback: VolleyCallback){
        url += "Bank_service/get_bank"
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

    fun insert(jsonBody: JSONObject){
        url = ip
        url += "Bank_service/insert"
        // Request a string response from the provided URL.
        val objectRequest = JsonObjectRequest(
            Request.Method.POST, url,jsonBody,
            Response.Listener<JSONObject> { response ->
            },
            Response.ErrorListener {
                    response-> Toast.makeText(context, "${response}", Toast.LENGTH_SHORT).show()
            }
        )
        objectRequest.setRetryPolicy(
            DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        )
        requestQueue?.add(objectRequest)
    }
}