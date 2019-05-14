package com.example.fms_app.Manage_accounting


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.fms_app.R
import com.example.fms_app.Service.Income
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.fragment_manage_income.*
import org.json.JSONArray
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Manage_income : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_income, container, false)

    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val object_ma: Income = Income(activity!!,cacheDir = activity!!.cacheDir)
       // val object_des: Description = Description(activity!!,cacheDir = activity!!.cacheDir)
        /*----------------------get_all_income------------------------------------*/
        object_ma.get_all(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {

            }

            override fun onSuccess(result: String) {
                var json = JSONArray(result)
                var data_report = get_data_table_inc("",0.00)
                val data_table = data_report.mapingData(json)
                //test_incomedata.text = data_income[0].Code
                table_recycle_view_inc.layoutManager = LinearLayoutManager(activity)
                table_recycle_view_inc.adapter = table_Adapter_inc(data_table)
                //val inc_code = JSONArray(result).getJSONObject(0).getString("inc_code")
            }
        })

        }



}
