package com.example.fms_app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.table_list.view.*

class table_Adapter (val datasource: ArrayList<get_data_table>) : RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val mView = LayoutInflater.from(p0.context)
        val mViewHolder = ViewHolder(mView, p0)

        return mViewHolder
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val reportData: get_data_table = datasource[p1]
        p0.binding(reportData)
    }

}

class ViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.table_list, parent, false)){
    var detail_table: TextView? = null

    var deatail_amount: TextView? = null

    init {
        detail_table = itemView.deatail_table

        deatail_amount = itemView.deatail_amount

    }

    fun binding(get_data_table: get_data_table){
        detail_table?.text = get_data_table.Detail

        deatail_amount?.text = get_data_table.Amount.toString()
    }

}