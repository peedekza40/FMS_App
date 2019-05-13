package com.example.fms_app


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_manage_bank_account.*
import org.json.JSONArray
import org.json.JSONException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Manage_description : Fragment() {

    private val TAG = "SERVICE_Basedata"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_manage_description, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val requestQueue = Volley.newRequestQueue(requireActivity())
        val url = "http://www.mocky.io/v2/5cd9a90b3000006621c01780"
        val stringRequest = serviceDataUTF8Encoding(Request.Method.GET,url,
            Response.Listener<String> { response ->
                try{
                    var json = JSONArray(response)
                    var data_desc = Description_data("","",0)
                    val desc_data = data_desc.mappingData(json)
                    desc_recycler.layoutManager = LinearLayoutManager(requireActivity())
                    desc_recycler.adapter = DescriptionListAdapter(desc_data)
                }catch (err : JSONException){

                }
            }
            , Response.ErrorListener { Toast.makeText(activity,"error", Toast.LENGTH_SHORT).show() })
        stringRequest.tag = TAG
        requestQueue?.add(stringRequest)
        val fab_add = getView()?.findViewById<FloatingActionButton>(R.id.add_desc_btn)

        /*fab_add?.setOnClickListener {
            var intent = Intent(activity,Add_bankAccount::class.java)
            startActivity(intent)
        }*/
    }

}
