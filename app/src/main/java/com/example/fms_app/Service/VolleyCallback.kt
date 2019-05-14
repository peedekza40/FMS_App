package com.example.fms_app.Service

import org.json.JSONArray
import org.json.JSONObject

interface VolleyCallback {
    fun onSuccess(result: String)
    fun onSuccess(result: JSONObject)
}