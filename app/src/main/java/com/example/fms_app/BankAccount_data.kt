package com.example.fms_app

import org.json.JSONArray

class BankAccount_data(var bacNo: String,
                       var bacName: String,
                       var baName: String,
                       var bacBranch: String,
                       var bacBalance: String,
                       var bacCurBalance: String) {
    fun mappingData(data: JSONArray) : ArrayList<BankAccount_data>{
        var bacDataListArray = ArrayList<BankAccount_data>()
        (0 until data.length()).mapTo(bacDataListArray){
            BankAccount_data(
                data.getJSONObject(it).getString("bacNo"),
                data.getJSONObject(it).getString("bacName"),
                data.getJSONObject(it).getString("baName"),
                data.getJSONObject(it).getString("bacBranch"),
                data.getJSONObject(it).getInt("bacBalance").toString(),
                data.getJSONObject(it).getInt("bacCurBalance").toString()
            )
        }
        return bacDataListArray
    }
}