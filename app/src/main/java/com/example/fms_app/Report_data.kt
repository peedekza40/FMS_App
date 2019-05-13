package com.example.fms_app

import org.json.JSONArray

class Report_data(var Code: String,
                  var Detail: String,
                  var Date: String,
                  var Balance: String){

    fun mapingData(data: JSONArray) : ArrayList<Report_data> {
        var reportDataListArr = ArrayList<Report_data>()
        (0 until data.length()).mapTo(reportDataListArr) {
            Report_data(
                data.getJSONObject(it).getString("inc_code"),
                data.getJSONObject(it).getString("inc_desc_id"),
                data.getJSONObject(it).getString("inc_date"),
                data.getJSONObject(it).getString("inc_amount")
            )

        }
        return reportDataListArr
    }

}