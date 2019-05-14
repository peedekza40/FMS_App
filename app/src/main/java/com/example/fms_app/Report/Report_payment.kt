package com.example.fms_app

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fms_app.Data_class.Report_data
import com.example.fms_app.Report.ReportListAdapter
import com.example.fms_app.Report.Report_Filter_payment
//import com.example.fms_app.Report.Report_Filter
import com.example.fms_app.Service.Payment
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.fragment_report_payment.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Report_payment: Fragment(){

    //private var startDate: String? = Report_Filter().date_start
    //private var endDate: String? = Report_Filter().date_end

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_report_payment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service_payment = Payment(activity!!.applicationContext,activity!!.cacheDir)
        service_payment.get_all(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                try {
                    var json = JSONArray(result)
                    var data_report = Report_data("","","","")
                    val data_payment = data_report.mapingData_payment(json)
                    Payment_recycler.layoutManager = LinearLayoutManager(requireActivity())
                    Payment_recycler.adapter = ReportListAdapter(data_payment)
                }catch (e: JSONException){
                    test_paymentdata.text = "Data not found or Service connection error"
                }
            }
        })

        Payment_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val positionView = (Payment_recycler.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

                if (positionView > 0) {
                    if(Search_payment_FAV.isShown) {
                        Search_payment_FAV.hide()
                    }
                } else  {
                    if(!Search_payment_FAV.isShown) {
                        Search_payment_FAV.show()
                    }
                }
            }

        })

        Search_payment_FAV.setOnClickListener {
            var intent: Intent = Intent(activity!!.applicationContext,Report_Filter_payment::class.java)
            startActivity(intent)
        }

    }

}