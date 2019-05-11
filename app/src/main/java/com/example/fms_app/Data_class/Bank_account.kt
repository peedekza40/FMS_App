package com.example.fms_app.Data_class

class BankAccount(
    var bacId: Int,
    var bacName: String
){
    fun get_bacId(): Int{
        return this.bacId
    }
    override fun toString(): String {
        return this.bacName
    }
}