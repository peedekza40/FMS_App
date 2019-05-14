package com.example.fms_app.Manage_base_data

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.fms_app.R
import com.example.fms_app.Service.Bank_account
import com.example.fms_app.Service.Description
import com.example.fms_app.Service.TransformDate
import kotlinx.android.synthetic.main.activity_add_description.*
import kotlinx.android.synthetic.main.fragment_add_income.*
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_add_description.view.*



class Add_description : AppCompatActivity() {

    var list_desc_type = arrayOf("รายรับ", "รายจ่าย")
    private var desc_type: Spinner? = null
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
        /*---------- Spinner --------------*/
        desc_type = this.txt_desctype
        //desc_type!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_desc_type)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        desc_type!!.setAdapter(aa)

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

    /*override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        Toast.makeText(this, list_desc_type[position], Toast.LENGTH_SHORT).show()
    }*/

    private fun insert_desc(){
        val text = desc_type?.getSelectedItem().toString()
        val service_desc = Description(this, cacheDir)
        if(this.validate()){
            val jsonBody = JSONObject()
            jsonBody.put("desc_desid", txt_descId!!.text.toString())
            jsonBody.put("desc_description", txt_descName!!.text.toString())
            if(text == "รายรับ"){
                jsonBody.put("desc_type", 1)
                jsonBody.put("ac_statusstat_id", 1)
            }else if(text == "รายจ่าย"){
                jsonBody.put("desc_type", 2)
                jsonBody.put("ac_statusstat_id", 1)
            }

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
