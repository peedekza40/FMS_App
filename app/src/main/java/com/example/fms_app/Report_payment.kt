package com.example.fms_app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_report_income.*
import kotlinx.android.synthetic.main.fragment_report_payment.*
import org.json.JSONArray
import org.json.JSONException
import java.io.Serializable

class Report_payment: Fragment(){

    private val TAG = "SERVICE_Report"
    //private var requestQueue: RequestQueue?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_report_payment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestQueue = Volley.newRequestQueue(requireActivity())
        val url = "http://www.mocky.io/v2/5ccfeff6320000b52100f90e"
        val stringRequest = serviceDataUTF8Encoding( Request.Method.GET, url,
            Response.Listener<String> { response ->

                try {
                    var json = JSONArray(response)
                    var data_report = Report_data("","","","")
                    val data_payment = data_report.mapingData(json)
                    //test_paymentdata.text = data_payment[0].Code
                    Payment_recycler.layoutManager = LinearLayoutManager(requireActivity())
                    Payment_recycler.adapter = ReportListAdapter(data_payment)
                }catch (e: JSONException){
                    test_paymentdata.text = e.message
                }

            },
            Response.ErrorListener { Toast.makeText(activity,"Error", Toast.LENGTH_LONG).show() })

        stringRequest.tag = this.TAG
        requestQueue?.add(stringRequest)

    }

}