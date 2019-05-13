package com.example.fms_app

import org.json.JSONArray

class BankAccount_data(var bacId: Int,
                       var bacNum: String,
                       var bacNo: String,
                       var Zbank: String,
                       var bacName: String,
                       var batId: Int,
                       var baId: Int,
                       var bacBranch: String,
                       var bacBalance: Int,
                       var bacCurBalance: Int,
                       var acId: Int,
                       var bacActive:String) {
    fun mappingData(data: JSONArray) : ArrayList<BankAccount_data>{
        var bacDataListArray = ArrayList<BankAccount_data>()
        (0 until data.length()).mapTo(bacDataListArray){
            BankAccount_data(
                data.getJSONObject(it).getInt("bacId"),
                data.getJSONObject(it).getString("bacNum"),
                data.getJSONObject(it).getString("bacNo"),
                data.getJSONObject(it).getString("ZBANK"),
                data.getJSONObject(it).getString("bacName"),
                data.getJSONObject(it).getInt("batId"),
                data.getJSONObject(it).getInt("baId"),
                data.getJSONObject(it).getString("bacBranch"),
                data.getJSONObject(it).getInt("bacBalance"),
                data.getJSONObject(it).getInt("bacCurBalance"),
                data.getJSONObject(it).getInt("acId"),
                data.getJSONObject(it).getString("bacActive")
            )
        }
        return bacDataListArray
    }
}