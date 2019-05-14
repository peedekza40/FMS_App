package com.example.fms_app.Data_class

import org.json.JSONArray

class BankAccount_data(var bacId: Int,
                       var bacNum: String,
                       var bacNo: String,
                       var bacName: String,
                       var baName: String,
                       var baCode: String,
                       var bacBranch: String,
                       var bacBalance: Int,
                       var bacCurBalance: Int,
                       var bacActive:String) {
    fun mappingData(data: JSONArray) : ArrayList<BankAccount_data>{
        var bacDataListArray = ArrayList<BankAccount_data>()
        (0 until data.length()).mapTo(bacDataListArray){
            BankAccount_data(
                data.getJSONObject(it).getInt("bacId"),
                data.getJSONObject(it).getString("bacNum"),
                data.getJSONObject(it).getString("bacNo"),
                data.getJSONObject(it).getString("bacName"),
                data.getJSONObject(it).getString("baName"),
                data.getJSONObject(it).getString("baCode"),
                data.getJSONObject(it).getString("bacBranch"),
                data.getJSONObject(it).getInt("bacBalance"),
                data.getJSONObject(it).getInt("bacCurBalance"),
                data.getJSONObject(it).getString("bacActive")
            )
        }
        return bacDataListArray
    }
}