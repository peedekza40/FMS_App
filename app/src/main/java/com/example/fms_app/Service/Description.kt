package com.example.fms_app.Service

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class Description (
    val context: Context,
    val cacheDir: File) : Conifg_service(context, cacheDir){

    init {
    }

    val TAG = "Service desc"

    fun get_desc_by_desctype(desctype : Int, callback: VolleyCallback){
        url = ip
        url += "/Description_service/get_desc_by_desctype?desc_type=" + desctype

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
    }//get_desc_by_desctype

    fun get_all_desc(callback: VolleyCallback){
        url += "/Description_service/get_all_desc"

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
    }//get_all_desc

    fun insert(jsonBody: JSONObject){
        url = ip
        url += "/Description_service/insert_desc"
        // Request a string response from the provided URL.
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {response ->
                println("${response}")
                Toast.makeText(context, "${response}", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                    response->
                    Toast.makeText(context, "${response}", Toast.LENGTH_SHORT).show()
            }) {
                override fun getBodyContentType(): String {
                    return "application/x-www-form-urlencoded; charset=UTF-8";
                }//getBodyContentType

                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["desc_desid"] = jsonBody.getString("desc_desid")
                    params["desc_description"] = jsonBody.getString("desc_description")
                    params["desc_type"] = jsonBody.getInt("desc_type").toString()
                    params["ac_statusstat_id"] = jsonBody.getInt("ac_statusstat_id").toString()
                    return params
                }//getParams
        }
        requestQueue?.add(stringRequest)
    }//insert

    fun cancleRequest(){
        requestQueue?.cancelAll(TAG)
    }

}