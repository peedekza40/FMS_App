package com.example.fms_app.Manage_base_data

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.fms_app.R
import kotlinx.android.synthetic.main.activity_add_bank_account.*

class Add_bank : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bank)
        val actionBar = supportActionBar
        actionBar!!.title = "เพิ่มข้อมูลธนาคาร"
        actionBar.setDisplayHomeAsUpEnabled(true)
        save_btn.setOnClickListener {
            finish()
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
