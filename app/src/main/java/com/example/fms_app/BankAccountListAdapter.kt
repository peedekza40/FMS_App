package com.example.fms_app

import android.content.Intent
import android.sax.StartElementListener
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import java.util.zip.Inflater
import kotlinx.android.synthetic.main.bac_list_view.view.*

class BankAccountListAdapter(val datasource: ArrayList<BankAccount_data>): RecyclerView.Adapter<BacViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BacViewHolder {
        val mView = LayoutInflater.from(p0.context)
        val mViewHolder = BacViewHolder(mView,p0)
        return mViewHolder
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(p0: BacViewHolder, p1: Int) {
        val bacData: BankAccount_data = datasource[p1]
        p0.binding(bacData)
    }

}

class BacViewHolder(inflater: LayoutInflater,parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.bac_list_view,parent,false)){
    var bacNo:TextView? = null
    var bacName:TextView? = null
    var baCode:TextView? = null
    var bacBalance:TextView? = null

    init {
        bacName = itemView.bacName
        baCode  = itemView.baCode
        bacBalance = itemView.bac_branch
    }

    fun binding(bankAccount_data: BankAccount_data){
        bacName?.text = "ชื่อบัญชี: " + bankAccount_data.bacName
        baCode?.text = "ธนาคาร: " + bankAccount_data.baCode
        bacBalance?.text = "คงเหลือ: " + bankAccount_data.bacBalance + ".-"
    }

}