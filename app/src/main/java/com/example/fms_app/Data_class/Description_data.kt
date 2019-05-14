package com.example.fms_app.Data_class

import org.json.JSONArray

class Description_data(var desc_desid: String,
                       var desc_description: String,
                       var desc_type: Int) {
    fun mappingData(data: JSONArray) : ArrayList<Description_data>{
        var descDataListArray = ArrayList<Description_data>()
        (0 until data.length()).mapTo(descDataListArray){
            Description_data(
                data.getJSONObject(it).getString("desc_desid"),
                data.getJSONObject(it).getString("desc_description"),
                data.getJSONObject(it).getInt("desc_type")
            )
        }
        return descDataListArray
    }//mappingData
}//Description_data