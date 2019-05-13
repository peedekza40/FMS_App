package com.example.fms_app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.bac_list_view.view.*
import kotlinx.android.synthetic.main.bac_list_view.view.descId
import kotlinx.android.synthetic.main.bac_list_view.view.descName
import kotlinx.android.synthetic.main.desc_list_view.view.*

class DescriptionListAdapter(val datasource: ArrayList<Description_data>): RecyclerView.Adapter<DescViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DescViewHolder {
        val mView = LayoutInflater.from(p0.context)
        val mViewHolder = DescViewHolder(mView,p0)
        return mViewHolder
    }//onCreateViewHolder

    override fun getItemCount(): Int {
        return datasource.size
    }//getItemCount

    override fun onBindViewHolder(p0: DescViewHolder, p1: Int) {
        val descData: Description_data = datasource[p1]
        p0.binding(descData)
    }//onBindViewHolder
}

class DescViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.desc_list_view,parent,false)){
    var descId: TextView? = null
    var descName: TextView? = null

    init {
        descId = itemView.descId
        descName = itemView.descName
    }

    fun binding(description_data: Description_data){
        descId?.text = description_data.desc_desid
        descName?.text = description_data.desc_description
    }//binding
}//DescViewHolder