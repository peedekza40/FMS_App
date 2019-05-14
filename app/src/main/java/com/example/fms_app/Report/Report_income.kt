package com.example.fms_app.Report

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fms_app.Data_class.Report_data
import com.example.fms_app.R
import com.example.fms_app.Service.Income
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.fragment_report_income.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

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
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                try {
                    var json = JSONArray(result)
                    var data_report = Report_data("", "", "", "")
                    val data_income = data_report.mapingData(json)
                    Income_recycler.layoutManager = LinearLayoutManager(requireActivity())
                    Income_recycler.adapter = ReportListAdapter(data_income)
                }catch (e: JSONException){
                    test_incomedata.text = e.message
                }
            }
        })

        Income_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val positionView = (Income_recycler.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

                if (positionView > 0) {
                    if(!Search_income_FAV.isShown) {
                        Search_income_FAV.show();
                    }
                } else  {
                    if(Search_income_FAV.isShown) {
                        Search_income_FAV.hide();
                    }
                }
            }

        })

        Search_income_FAV.setOnClickListener {
            var intent: Intent = Intent(activity!!.applicationContext, Report_Filter::class.java)
            startActivity(intent)
        }

    }


}