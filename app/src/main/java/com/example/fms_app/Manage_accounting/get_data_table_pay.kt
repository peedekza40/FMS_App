package com.example.fms_app.Manage_accounting


import org.json.JSONArray

class get_data_table_py (var desc_description: String,
                      var pay_amount: Double
){

    fun mapingData(data: JSONArray) : ArrayList<get_data_table_py> {
        var getDataListArr = ArrayList<get_data_table_py>()
        (0 until data.length()).mapTo(getDataListArr) {
            get_data_table_py(
                data.getJSONObject(it).getString("desc_description"),
                data.getJSONObject(it).getDouble("pay_amount")
            )

        }
        return getDataListArr
    }

}