package com.example.fms_app.Service

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
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
        url += "/Bank_service/get_bank"
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
        url += "/Bank_service/insert"
        // Request a string response from the provided URL.
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {response ->
                Toast.makeText(context, "${response}", Toast.LENGTH_SHORT).show()
                println("${response}")
            },
            Response.ErrorListener {
                    response-> Toast.makeText(context, "${response}", Toast.LENGTH_SHORT).show()
            }){
            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["baCode"] = jsonBody.getString("baCode")
                params["baName"] = jsonBody.getString("baName")
                return params
            }
        }
        requestQueue?.add(stringRequest)
    }
}