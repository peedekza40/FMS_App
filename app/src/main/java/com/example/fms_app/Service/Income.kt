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
import com.android.volley.AuthFailureError






class Income(
    val context: Context,
    val cacheDir: File): Conifg_service(context, cacheDir){

    val TAG = "Service Inc"
    init {
    }

    fun get_all(callback: VolleyCallback){
        url = ip
        url += "/Income_service/get_all_income"
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
        url += "/Income_service/get_last_inc_code"
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

    fun insert(jsonBody: JSONObject, callback: VolleyCallback){
        url = ip
        url += "/Income_service/insert_income"
        // Request a string response from the provided URL.
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {response ->
                callback.onSuccess(response)
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
                    params["inc_code"] = jsonBody.getString("inc_code")
                    params["inc_date"] = jsonBody.getString("inc_date")
                    params["inc_receipt"] = jsonBody.getString("inc_receipt")
                    params["inc_receipt_code"] = jsonBody.getString("inc_receipt_code")
                    params["inc_receipt_date"] = jsonBody.getString("inc_receipt_date")
                    params["inc_amount"] = jsonBody.getInt("inc_amount").toString()
                    params["inc_detail"] = jsonBody.getString("inc_detail")
                    params["inc_desc_id"] = jsonBody.getInt("inc_desc_id").toString()
                    params["inc_bac_id"] = jsonBody.getInt("inc_bac_id").toString()
                    return params
                }
            }
        requestQueue?.add(stringRequest)
    }

    fun get_all_rangedate_bacid(date_start:String?,date_end: String?,bacId: String?,callback: VolleyCallback){
        url = ip
        url += "/Income_service/get_by_rangedate_and_bac?startDate=${date_start}&endDate=${date_end}&bacId=${bacId}"
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

    fun cancleRequest(){
        requestQueue?.cancelAll(TAG)
    }
}