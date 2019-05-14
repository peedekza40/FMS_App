package com.example.fms_app.Data_class

import org.json.JSONArray

class Bank_data(var baId: Int,
                var baCode: String,
                var baName: String) {
    fun mappingData(data: JSONArray) : ArrayList<Bank_data>{
        var baDataListArray = ArrayList<Bank_data>()
        (0 until data.length()).mapTo(baDataListArray){
            Bank_data(
                data.getJSONObject(it).getInt("baId"),
                data.getJSONObject(it).getString("baCode"),
                data.getJSONObject(it).getString("baName")
            )
        }
        return baDataListArray
    }

    override fun toString(): String {
        return baName
    }
}