package com.example.fms_app.Manage_accounting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.android.volley.RequestQueue
import android.support.v4.view.ViewPager
import android.support.design.widget.TabLayout
import com.example.fms_app.R
import com.example.fms_app.Adapter_center.TabAdapter


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

        /*-----------------set tap-------------------*/
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        tapAdapter = TabAdapter(supportFragmentManager)

        tapAdapter!!.addFragment(add_income(), "รายรับ")
        tapAdapter!!.addFragment(add_payment(), "รายจ่าย")

        viewPager!!.setAdapter(tapAdapter)
        tabLayout!!.setupWithViewPager(viewPager)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
