package com.example.fms_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_bank_account.*

class Add_description : AppCompatActivity() {

    private var txt_descId: TextView? = null
    private var txt_descName: TextView? = null
    private var save_btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_description)
        val actionBar = supportActionBar
        actionBar!!.title = "เพิ่มข้อมูลคำอธิบายรายการบัญชี"
        actionBar.setDisplayHomeAsUpEnabled(true)
        save_btn = findViewById(R.id.save_btn) as Button
        txt_descId = findViewById(R.id.txt_descId) as TextView
        txt_descName = findViewById(R.id.txt_descName) as TextView
        save_btn!!.setOnClickListener {
            insert_desc()
        }
    }//onCreate

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }//onOptionsItemSelected

    private fun insert_desc(){
        finish()
    }//insert_desc
}
