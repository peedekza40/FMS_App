package com.example.fms_app.Manage_base_data

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.fms_app.Data_class.Bank_data
import com.example.fms_app.R
import com.example.fms_app.Service.Bank
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.activity_add_bank_account.*
import kotlinx.android.synthetic.main.fragment_add_income.*
import kotlinx.android.synthetic.main.fragment_manage_bank.*
import org.json.JSONArray
import org.json.JSONObject

class Add_bank : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bank)
        val actionBar = supportActionBar
        actionBar!!.title = "เพิ่มข้อมูลธนาคาร"
        actionBar.setDisplayHomeAsUpEnabled(true)
//        save_btn.setOnClickListener {
//            finish()
//        }
        var input_baCode = findViewById<EditText>(R.id.txt_baCode)
        var input_baName = findViewById<EditText>(R.id.txt_baName)
        val serviceBank = Bank(this!!.applicationContext,this!!.cacheDir)

        // click action on save button
        save_btn.setOnClickListener {
            // Check can insert to db
            var baCode = input_baCode.text.toString()
            var baName = input_baName.text.toString()

            if (baName != "" && baCode != "") {
                val jsonBody = JSONObject()
                jsonBody.put("baCode", input_baCode.text.toString())
                jsonBody.put("baName", input_baName.text.toString())
                serviceBank.insert(jsonBody)
                Toast.makeText(this, "Add Bank Success", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Can't Add Bank", Toast.LENGTH_SHORT).show()
            }
        }
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
