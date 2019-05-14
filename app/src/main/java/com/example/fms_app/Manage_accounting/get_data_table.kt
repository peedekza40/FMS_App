package com.example.fms_app.Manage_accounting

import org.json.JSONArray

class get_data_table (var desc_description: String,
                      var inc_amount: Double
){

    fun mapingData(data: JSONArray) : ArrayList<get_data_table> {
        var getDataListArr = ArrayList<get_data_table>()
        (0 until data.length()).mapTo(getDataListArr) {
            get_data_table(
                data.getJSONObject(it).getString("desc_description"),
                data.getJSONObject(it).getDouble("inc_amount")
            )

        }
        return getDataListArr
    }

}