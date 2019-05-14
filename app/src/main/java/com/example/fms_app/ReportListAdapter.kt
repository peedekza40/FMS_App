package com.example.fms_app

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.report_list_view.view.*

class ReportListAdapter(val datasource: ArrayList<Report_data>) : RecyclerView.Adapter<reportViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): reportViewHolder {
        val mView = LayoutInflater.from(p0.context)
        val mViewHolder = reportViewHolder(mView, p0)

        return mViewHolder
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(p0: reportViewHolder, p1: Int) {
        val reportData: Report_data = datasource[p1]
        p0.binding(reportData)
    }

}

class reportViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.report_list_view, parent, false)){
    var code_report: TextView? = null
    var detail_report: TextView? = null
    var date_report: TextView? = null
    var balance_report: TextView? = null

    init {
        code_report = itemView.report_code
        detail_report = itemView.report_detail
        date_report = itemView.report_date
        balance_report = itemView.report_Balance
    }

    fun binding(reportData: Report_data){
        code_report?.text = reportData.Code
        detail_report?.text = reportData.Detail
        date_report?.text = reportData.Date
        balance_report?.text = reportData.Balance
    }

}