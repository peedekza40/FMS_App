package com.example.fms_app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fms_app.Service.Income
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.fragment_report_income.*
import org.json.JSONArray
import org.json.JSONException
import java.io.UnsupportedEncodingException

class Report_income: Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_report_income, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service_income = Income(activity!!.applicationContext,activity!!.cacheDir)
        service_income.get_all(object : VolleyCallback {
            override fun onSuccess(result: String) {
               try {
                   var json = JSONArray(result)
                   var data_report = Report_data("","","","")
                   val data_income = data_report.mapingData(json)
                   Income_recycler.layoutManager = LinearLayoutManager(requireActivity())
                   Income_recycler.adapter = ReportListAdapter(data_income)
               }catch (e: JSONException){
                   test_incomedata.text = e.message
               }
            }
        })


    }


}