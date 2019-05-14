package com.example.fms_app.Manage_base_data


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.toolbox.Volley
import com.example.fms_app.Data_class.BankAccount_data
import com.example.fms_app.R
import com.example.fms_app.Service.Bank_account
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.fragment_manage_bank_account.*
import org.json.JSONArray
import org.json.JSONObject

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
        val serviceBac = Bank_account(activity!!.applicationContext,activity!!.cacheDir)

        serviceBac.get_bankAccount(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                val bac_frm_svc = JSONArray(result)
                var data_bac = BankAccount_data(0, "", "", "", "", "", "", 0, 0, "")
                val bac_data = data_bac.mappingData(bac_frm_svc)
                bac_recycler.layoutManager = LinearLayoutManager(requireActivity())
                bac_recycler.adapter = BankAccountListAdapter(bac_data)
                bac_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val positionView = (bac_recycler.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

                        if (positionView > 0) {
                            if(!add_bac_btn.isShown) {
                                add_bac_btn.show();
                            }
                        } else  {
                            if(add_bac_btn.isShown) {
                                add_bac_btn.hide();
                            }
                        }
                    }

                })
            }
        })

        val fab_add = getView()?.findViewById<FloatingActionButton>(R.id.add_bac_btn)

        fab_add?.setOnClickListener {
            var intent = Intent(activity, Add_bankAccount::class.java)
            startActivity(intent)
        }

    }

}
