package com.example.fms_app

import org.json.JSONArray

class get_data_table (var Detail: String,
                  var Amount: Double
                 ){

    fun mapingData(data: JSONArray) : ArrayList<get_data_table> {
        var getDataListArr = ArrayList<get_data_table>()
        (0 until data.length()).mapTo(getDataListArr) {
            get_data_table(
                data.getJSONObject(it).getString("Detail"),
                data.getJSONObject(it).getDouble("Amount")
            )

        }
        return getDataListArr
    }

}