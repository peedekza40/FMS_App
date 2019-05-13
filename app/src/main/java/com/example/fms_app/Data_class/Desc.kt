package com.example.fms_app.Data_class

data class Desc (
    var desc_id: Int,
    var desc_desid: String,
    var desc_description : String,
    var desc_type: Int
){
    override fun toString(): String{
        return desc_description
    }
}