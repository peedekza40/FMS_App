package com.example.fms_app

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import java.io.Serializable


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "SERVICE_Report"
    private var requestQueue: RequestQueue?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /*-----------add floating btn action---------------------*/
//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        this.getIncomeData()
        this.getPaymentData()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.add_btn, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item!!.itemId) {
            R.id.add_ac_btn -> {
                //Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Add_accounting::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            when (requestCode){
                1 ->{
                    Toast.makeText(this, "Success!!" + requestCode, Toast.LENGTH_SHORT).show()
                }
                2 ->{
                    Toast.makeText(this, "Success!!" + requestCode, Toast.LENGTH_SHORT).show()
                }
                3 ->{
                    Toast.makeText(this, "Success!!" + requestCode, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_base_data -> {
                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                val newFragment = Manage_base_data()
                transaction.replace(R.id.container, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            R.id.nav_accounting -> {
                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                val newFragment = Manage_ac()
                transaction.replace(R.id.container, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            R.id.nav_report -> {
                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                val newFragment = Report()
                transaction.replace(R.id.container, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            R.id.nav_dashboard -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    fun getIncomeData(){

        val url = "http://www.mocky.io/v2/5ccfb106320000630000f77f"
        val stringRequest = StringRequest( Request.Method.GET, url,
            Response.Listener<String> { response ->
                var json = JSONArray(response)
                var data_report = Report_data("","","","")
                val data_income = data_report.mapingData(json)
                Report_income().arguments = Bundle().apply {
                    putSerializable("income_report_data",data_income as Serializable)
                }

            },
            Response.ErrorListener { Toast.makeText(this,"Error",Toast.LENGTH_LONG).show() })

        stringRequest.tag = this.TAG
        requestQueue?.add(stringRequest)

    }

    fun getPaymentData(){

        val url = "http://www.mocky.io/v2/5ccfeff6320000b52100f90e"
        val stringRequest = StringRequest( Request.Method.GET, url,
            Response.Listener<String> { response ->
                var json = JSONArray(response)
                var data_report = Report_data("","","","")
                val data_payment = data_report.mapingData(json)
                Report_payment().arguments = Bundle().apply {
                    putSerializable("payment_report_data",data_payment as Serializable)
                }
            },
            Response.ErrorListener { Toast.makeText(this,"Error",Toast.LENGTH_LONG).show() })

        stringRequest.tag = this.TAG
        this.requestQueue?.add(stringRequest)
    }
}


