package com.example.fms_app.Service

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import java.io.File
import kotlin.collections.ArrayList
import org.json.JSONObject
import com.example.fms_app.Service.Conifg_service



class Payment(
    val context: Context,
    val cacheDir: File): Conifg_service(context, cacheDir){
    val TAG = "Service Pay"
    init {
    }

    fun get_all(callback: VolleyCallback){
        url = ip
        url += "/Payment_service/get_all_payment"
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

    fun get_last_pay_code(callback: VolleyCallback){
        url = ip
        url += "/Payment_service/get_last_pay_code"
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
        url += "/Payment_service/insert_payment"
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
                params["pay_code"] = jsonBody.getString("pay_code")
                params["pay_date"] = jsonBody.getString("pay_date")
                params["pay_receipt"] = jsonBody.getString("pay_receipt")
                params["pay_receipt_code"] = jsonBody.getString("pay_receipt_code")
                params["pay_receipt_date"] = jsonBody.getString("pay_receipt_date")
                params["pay_amount"] = jsonBody.getInt("pay_amount").toString()
                params["pay_detail"] = jsonBody.getString("pay_detail")
                params["pay_desc_id"] = jsonBody.getInt("pay_desc_id").toString()
                params["pay_bac_id"] = jsonBody.getInt("pay_bac_id").toString()
                return params
            }
        }
        requestQueue?.add(stringRequest)
    }

    fun getByRangeDate(date_start:String?,date_end: String?,bacId: String?,callback: VolleyCallback){
        url += "/Payment_service/get_by_rangedate_and_bac?startDate=${date_start}&endDate=${date_end}&bacId=${bacId}"
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


}