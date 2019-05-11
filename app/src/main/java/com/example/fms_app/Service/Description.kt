package com.example.fms_app.Service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.io.File

class Description (
    val context: Context,
    val cacheDir: File) : Conifg_service(context, cacheDir){

    fun get_desc_by_desctype(desctype : Char, callback: VolleyCallback){
        url += "/get_desc_by_desctype"
        val jsonBody = JSONObject()
        jsonBody.put("desctype", desctype)

        // Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url, jsonBody,
            Response.Listener<JSONObject> { response ->
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