package com.example.fms_app.Data_class

import org.json.JSONArray

class Report_data(var Code: String,
                  var Detail: String,
                  var Date: String,
                  var Balance: String){

    fun mapingData_income(data: JSONArray) : ArrayList<Report_data> {
        var reportDataListArr = ArrayList<Report_data>()
        (0 until data.length()).mapTo(reportDataListArr) {
            Report_data(
                data.getJSONObject(it).getString("inc_code"),
                data.getJSONObject(it).getString("desc_description"),
                data.getJSONObject(it).getString("inc_date"),
                data.getJSONObject(it).getString("inc_amount")
            )

        }
        return reportDataListArr
    }

    fun mapingData_payment(data: JSONArray) : ArrayList<Report_data> {
        var reportDataListArr = ArrayList<Report_data>()
        (0 until data.length()).mapTo(reportDataListArr) {
            Report_data(
                data.getJSONObject(it).getString("pay_code"),
                data.getJSONObject(it).getString("desc_description"),
                data.getJSONObject(it).getString("pay_date"),
                data.getJSONObject(it).getString("pay_amount")
            )

        }
        return reportDataListArr
    }

}