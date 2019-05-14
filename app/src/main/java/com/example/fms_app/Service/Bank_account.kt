package com.example.fms_app.Service

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.io.File
import org.json.JSONObject

class Bank_account (
    val context: Context,
    val cacheDir: File) : Conifg_service(context, cacheDir){

    val TAG = "Service Bac"
    fun get_bankAccount(callback: VolleyCallback){
        url += "/Bankaccount_service/get_bankAccount"
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
        url += "/Bankaccount_service/insert_bac"
        // Request a string response from the provided URL.
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {response ->
                Toast.makeText(context, "${response}", Toast.LENGTH_SHORT).show()
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
                params["bacNum"] = jsonBody.getString("bacNum")
                params["bacNo"] = jsonBody.getString("bacNo")
                params["ZBANK"] = jsonBody.getString("ZBANK")
                params["bacName"] = jsonBody.getString("bacName")
                params["batId"] = jsonBody.getInt("batId").toString()
                params["baId"] = jsonBody.getInt("baId").toString()
                params["bacBranch"] = jsonBody.getString("bacBranch")
                params["bacBalance"] = jsonBody.getInt("bacBalance").toString()
                return params
            }
        }
        requestQueue?.add(stringRequest)
    }

    fun cancleRequest(){
        requestQueue?.cancelAll(TAG)
    }
}