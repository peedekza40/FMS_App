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
import com.example.fms_app.Data_class.Description_data
import com.example.fms_app.R
import com.example.fms_app.Service.Description
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.fragment_manage_description.*
import org.json.JSONArray
import org.json.JSONObject

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
    }//onCreate

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
        val service_desc = Description(activity!!.applicationContext, activity!!.cacheDir)
        /*----------------------get all description------------------------------------*/
        service_desc.get_all_desc(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                val desc_frm_srv = JSONArray(result)
                var data_desc = Description_data("", "", 0)
                val desc_data = data_desc.mappingData(desc_frm_srv)
                desc_recycler.layoutManager = LinearLayoutManager(requireActivity())
                desc_recycler.adapter = DescriptionListAdapter(desc_data)
                desc_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val positionView = (desc_recycler.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

                        if (positionView > 0) {
                            if(!add_desc_btn.isShown) {
                                add_desc_btn.show();
                            }//if
                        } else  {
                            if(add_desc_btn.isShown) {
                                add_desc_btn.hide();
                            }//if
                        }//else
                    }//onScrolled
                })
            }//onSuccess
        })

        /*----------------------Mock Data------------------------------------*/
        /*val requestQueue = Volley.newRequestQueue(requireActivity())
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
        requestQueue?.add(stringRequest)*/
        val fab_add = getView()?.findViewById<FloatingActionButton>(R.id.add_desc_btn)

        fab_add?.setOnClickListener {
            var intent = Intent(activity, Add_description::class.java)
            startActivity(intent)
        }
    }//onViewCreated
}//Manage_description
