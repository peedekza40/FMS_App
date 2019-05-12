package com.example.fms_app


import android.app.DownloadManager
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_manage_bank_account.*
import org.json.JSONArray
import org.json.JSONException

class Manage_bankAccount : Fragment() {

    private val TAG = "SERVICE_Basedata"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_manage_bank_account, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val requestQueue = Volley.newRequestQueue(requireActivity())
        val url = "http://www.mocky.io/v2/5cd8353d300000a22a74cd4c"
        val stringRequest = serviceDataUTF8Encoding(Request.Method.GET,url,
            Response.Listener<String> { response ->
                try{
                    var json = JSONArray(response)
                    var data_bac = BankAccount_data("","","","","","")
                    val bac_data = data_bac.mappingData(json)
                    bac_recycler.layoutManager = LinearLayoutManager(requireActivity())
                    bac_recycler.adapter = BankAccountListAdapter(bac_data)
                }catch (err : JSONException){

                }
            }
            ,Response.ErrorListener { Toast.makeText(activity,"error",Toast.LENGTH_SHORT).show() })
        stringRequest.tag = TAG
        requestQueue?.add(stringRequest)
        val fab_add = getView()?.findViewById<FloatingActionButton>(R.id.add_bac_btn)
//        val pencil_btn = getView()?.findViewById<ImageButton>(R.id.pen_bac_btn)

        fab_add?.setOnClickListener {
            var intent = Intent(activity,Add_bankAccount::class.java)
            startActivity(intent)
        }

//        pencil_btn?.setOnClickListener {
//            var intent = Intent(activity,Edit_bankaccount::class.java)
//            startActivity(intent)
//        }
    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//
//        return when (item!!.itemId) {
//            R.id.pen_bac_btn -> {
//                //Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show()
//                val intent = Intent(activity, Edit_bankaccount::class.java)
//                startActivity(intent)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
