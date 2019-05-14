package com.example.fms_app


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnLongClickListener;
import android.widget.Toast
import com.android.volley.toolbox.Volley
import com.example.fms_app.Service.Bank
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.fragment_manage_bank.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Manage_bank : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_bank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val requestQueue = Volley.newRequestQueue(requireActivity())
        //val url = "http://www.mocky.io/v2/5cd9a43b3000006f35c01762"
        val serviceBank = Bank(activity!!.applicationContext,activity!!.cacheDir)
//        val stringRequest = serviceDataUTF8Encoding(Request.Method.GET,url,
//            Response.Listener<String> { response ->
//                try{
//                    var json = JSONArray(response)
//                    var data_bank = Bank_data("","")
//                    val bank_data = data_bank.mappingData(json)
//                    bank_recycler.layoutManager = LinearLayoutManager(requireActivity())
//                    bank_recycler.adapter = BankListAdapter(bank_data)
//                }catch (err : JSONException){
//
//                }
//            }
//            ,Response.ErrorListener { Toast.makeText(activity,"error", Toast.LENGTH_SHORT).show() })
//        stringRequest.tag = TAG
//        requestQueue?.add(stringRequest)
        serviceBank.get_bank(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                val bank_frm_svc = JSONArray(result)
                var data_bank = Bank_data("","")
                val bank_data = data_bank.mappingData(bank_frm_svc)
//                val bank = ArrayList<Bank>()
                bank_recycler.layoutManager = LinearLayoutManager(requireActivity())
                bank_recycler.adapter = BankListAdapter(bank_data)
                bank_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val positionView = (bank_recycler.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

                        if (positionView > 0) {
                            if(!add_bank_btn.isShown) {
                                add_bank_btn.show();
                            }
                        } else  {
                            if(add_bank_btn.isShown) {
                                add_bank_btn.hide();
                            }
                        }
                    }

                })
            }
        })
        val fab_add = getView()?.findViewById<FloatingActionButton>(R.id.add_bank_btn)
//        val pencil_btn = getView()?.findViewById<ImageButton>(R.id.pen_bac_btn)

        fab_add?.setOnClickListener {
            var intent = Intent(activity,Add_bank::class.java)
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
