package com.example.fms_app.Manage_base_data

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.fms_app.R
import com.example.fms_app.Service.Bank_account
import com.example.fms_app.Service.Description
import com.example.fms_app.Service.TransformDate
import kotlinx.android.synthetic.main.fragment_add_income.*
import org.json.JSONObject

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
        val service_desc = Description(this, cacheDir)
        if(this.validate()){
            val jsonBody = JSONObject()
            jsonBody.put("desc_desid", txt_descId?.text.toString())
            jsonBody.put("desc_description", txt_descName?.text.toString())
            jsonBody.put("desc_type", 2)
            jsonBody.put("ac_statusstat_id", 1)
            service_desc.insert(jsonBody)
            finish()
        }else{
            Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()
        }
    }//insert_desc

    fun validate(): Boolean{
        val service_desc = Description(this, cacheDir)
        var result: Boolean = true
        if(txt_descId?.text.toString() == ""){
            txt_descId?.setError( "กรุณากรอกเลขคำอธิบายรายการบัญชี" )
            result = false
        }
        if(txt_descName?.text.toString() == ""){
            txt_descName?.setError( "กรุณากรอกคำอธิบายรายการบัญชี" )
            result = false
        }
        return result
    }
}
