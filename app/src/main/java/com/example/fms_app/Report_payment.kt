package com.example.fms_app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.fragment_report_income.*
import kotlinx.android.synthetic.main.fragment_report_payment.*

class Report_payment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_report_payment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey("payment_report_data") }?.apply{
            val data: ArrayList<Report_data> = getSerializable("payment_report_data") as ArrayList<Report_data>
            PaymentRecycler.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ReportListAdapter(data)
            }
        }
    }

}