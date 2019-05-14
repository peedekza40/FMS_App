package com.example.fms_app.Data_class

import org.json.JSONArray

class BacType_data(var batId:Int,
                   var batName: String,
                   var batActive: String) {
    fun mappingData(data: JSONArray) : ArrayList<BacType_data>{
        var batDataListArray = ArrayList<BacType_data>()
        (0 until data.length()).mapTo(batDataListArray){
            BacType_data(
                data.getJSONObject(it).getInt("batId"),
                data.getJSONObject(it).getString("batName"),
                data.getJSONObject(it).getString("batActive")
            )
        }
        return batDataListArray
    }

    override fun toString(): String {
        return batName
    }
}