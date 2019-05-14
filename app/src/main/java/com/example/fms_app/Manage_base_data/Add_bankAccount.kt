package com.example.fms_app.Manage_base_data

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import com.example.fms_app.R
import kotlinx.android.synthetic.main.activity_add_bank_account.*

class Add_bankAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bank_account)
        val actionBar = supportActionBar
        actionBar!!.title = "เพิ่มข้อมูลบัญชีเงินฝาก"
        actionBar.setDisplayHomeAsUpEnabled(true)
        var input_bacId = findViewById<EditText>(R.id.text_bacId)
        var input_bacNo = findViewById<EditText>(R.id.text_bacNo)
        var input_zbank = findViewById<EditText>(R.id.text_Zbank)
        var input_bacName = findViewById<EditText>(R.id.text_bacName)
        var input_batId = findViewById<EditText>(R.id.text_batId)
        var input_baId = findViewById<EditText>(R.id.text_baId)
        var input_balance = findViewById<EditText>(R.id.text_balance)
        save_btn.setOnClickListener {
            var bacId =input_bacId.text.toString()
            var bacNo =input_bacNo.text.toString()
            var zbank =input_zbank.text.toString()
            var bacName =input_bacName.text.toString()
            var batId =input_batId.text.toString()
            var baId =input_baId.text.toString()
            var balance =input_balance.text.toString()
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
