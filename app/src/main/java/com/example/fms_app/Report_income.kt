package com.example.fms_app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_report_income.*
import org.json.JSONArray

class Report_income: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_report_payment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey("income_report_data") }?.apply{
            val data: ArrayList<Report_data> = getSerializable("income_report_data") as ArrayList<Report_data>
            IncomeRecycler.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ReportListAdapter(data)
            }
        }
    }

}