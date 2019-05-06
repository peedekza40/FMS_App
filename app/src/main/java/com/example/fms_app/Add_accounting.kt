package com.example.fms_app

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.NavUtils
import android.view.MenuItem
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.example.fms_app.Service.Income
import com.example.fms_app.Service.VolleyCallback
import org.json.JSONArray
import kotlinx.android.synthetic.main.activity_add_accounting.*
import android.support.v4.view.ViewPager
import android.support.design.widget.TabLayout
import android.widget.EditText


class Add_accounting : AppCompatActivity() {

    var requestQueue: RequestQueue? = null
    private var tapAdapter: TabAdapter? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_accounting)

        /*-----------------set action bar-------------------*/
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "เพิ่มรายการบัญชี"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        /*-----------------use service-------------------*/
        val service_income = Income(this,cacheDir)
        service_income.get_all(object : VolleyCallback {
            override fun onSuccess(result: String) {
                //codeText.text = JSONArray(result).getJSONObject(0).getString("inc_code")
            }
        })

        /*-----------------set tap-------------------*/
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        tapAdapter = TabAdapter(supportFragmentManager)

        tapAdapter!!.addFragment(add_income(), "รายรับ")
        tapAdapter!!.addFragment(add_payment(), "รายจ่าย")

        viewPager!!.setAdapter(tapAdapter)
        tabLayout!!.setupWithViewPager(viewPager)

    }




//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item!!.itemId) {
//            android.R.id.home -> {
//                finish()
//                return true
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
}
