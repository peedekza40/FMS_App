package com.example.fms_app

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
    var baName:TextView? = null
    var bacBalance:TextView? = null

    init {
        bacNo = itemView.bacNo
        bacName = itemView.bacName
        baName  = itemView.baName
        bacBalance = itemView.bacBalance
    }

    fun binding(bankAccount_data: BankAccount_data){
        bacNo?.text = bankAccount_data.bacNo
        baName?.text = bankAccount_data.bacName
        baName?.text = bankAccount_data.baId
        bacBalance?.text = bankAccount_data.bacBalance
    }

}