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
                data.getJSONObject(it).getString("Code"),
                data.getJSONObject(it).getString("Detail"),
                data.getJSONObject(it).getString("Date"),
                data.getJSONObject(it).getInt("Balance").toString()
            )

        }
        return reportDataListArr
    }

}