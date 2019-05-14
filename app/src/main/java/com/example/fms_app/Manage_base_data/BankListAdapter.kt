package com.example.fms_app.Manage_base_data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.fms_app.Data_class.Bank_data
import com.example.fms_app.R
import kotlinx.android.synthetic.main.ba_list_view.view.*

class BankListAdapter(val datasource: ArrayList<Bank_data>): RecyclerView.Adapter<BaViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaViewHolder {
        val mView = LayoutInflater.from(p0.context)
        val mViewHolder = BaViewHolder(mView, p0)
        return mViewHolder
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(p0: BaViewHolder, p1: Int) {
        val baData: Bank_data = datasource[p1]
        p0.binding(baData)
    }

}

class BaViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.ba_list_view,parent,false)) {
    var baCode: TextView? = null
    var baName: TextView? = null

    init {
        baCode = itemView.baCode
        baName = itemView.baName
    }

    fun binding(bank_data: Bank_data) {
        baCode?.text = bank_data.baCode
        baName?.text = bank_data.baName
    }
}