package com.example.fms_app.Manage_base_data

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.fms_app.Data_class.BacType_data
import com.example.fms_app.Data_class.Bank_data
import com.example.fms_app.R
import com.example.fms_app.Service.*
import kotlinx.android.synthetic.main.activity_add_bank_account.*
import org.json.JSONArray
import org.json.JSONObject

class Add_bankAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bank_account)
        val actionBar = supportActionBar
        actionBar!!.title = "เพิ่มข้อมูลบัญชีเงินฝาก"
        //-------------- bank dropdown --------------------

        val service_bank = Bank(this!!.applicationContext,this!!.cacheDir)
        val service_bacType = BankAccountType(this!!.applicationContext,this!!.cacheDir)
        val service_bankAccount = Bank_account(this!!.applicationContext,this!!.cacheDir)
        /*---------------------set bank dropdown-------------------------*/
        text_baId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                text_baId.setError(null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        service_bank.get_bank(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                val ba_frm_svc = JSONArray(result)
                var data_bank = Bank_data(0,"", "")
                val bank_data = data_bank.mappingData(ba_frm_svc)
//                println(data_bank)


                //Create Array Adapter
                val adapter = ArrayAdapter<Bank_data>(this@Add_bankAccount, android.R.layout.simple_dropdown_item_1line, bank_data)
                text_baId.setAdapter(adapter)
            }
        })
        text_baId.setOnFocusChangeListener { v, hasFocus ->
            text_baId.showDropDown()
        }
        text_baId.setOnClickListener {
            text_baId.showDropDown()
        }

        text_baId.setOnItemClickListener(AdapterView.OnItemClickListener { arg0, arg1, arg2, arg3 ->
            val result = arg0.getItemAtPosition(arg2) as Bank_data
            baId.setText(result.baId.toString())//TextView in layout is visible = gone
        })

        /*---------------------set bankaccount type dropdown-------------------------*/
        text_batId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                text_batId.setError(null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        service_bacType.get_bankaccountType(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                val bat_frm_svc = JSONArray(result)
                var data_bat = BacType_data(0,"", "")
                val bat_data = data_bat.mappingData(bat_frm_svc)

                //Create Array Adapter
                val adapter = ArrayAdapter<BacType_data>(this@Add_bankAccount, android.R.layout.simple_dropdown_item_1line, bat_data)
                text_batId.setAdapter(adapter)
            }
        })
        text_batId.setOnFocusChangeListener { v, hasFocus ->
            text_batId.showDropDown()
        }
        text_batId.setOnClickListener {
            text_batId.showDropDown()
        }

        text_batId.setOnItemClickListener(AdapterView.OnItemClickListener { arg0, arg1, arg2, arg3 ->
            val result = arg0.getItemAtPosition(arg2) as BacType_data
            batId.setText(result.batId.toString())//TextView in layout is visible = gone
        })

        //---------------------------------- end drop down --------------------------------------------

        actionBar.setDisplayHomeAsUpEnabled(true)
        save_btn.setOnClickListener {
            if(this.validate()){
                val jsonBody = JSONObject()
                jsonBody.put("bacNum", text_bacnum.text.toString())
                jsonBody.put("bacNo", text_bacNo.text.toString())
                jsonBody.put("ZBANK", text_Zbank.text.toString())
                jsonBody.put("bacName", text_bacName.text.toString())
                jsonBody.put("batId", batId.text.toString().toInt())
                jsonBody.put("baId", baId.text.toString().toInt())
                jsonBody.put("bacBranch", text_bacBranch.text.toString())
                jsonBody.put("bacBalance", text_balance.text.toString().toInt())
                service_bankAccount.insert(jsonBody)
                Toast.makeText(this, "บันทึกข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()

            }
        }
    }
    fun validate(): Boolean{
        var result: Boolean = true
        if(text_bacnum.text.toString() == ""){
            text_bacnum.setError( "กรุณากรอกรหัสบัญชี" )
            result = false
        }
        if(text_bacNo.text.toString() == ""){
            text_bacNo.setError( "กรุณากรอกเลขที่บัญชี" )
            result = false
        }
        if(text_bacName.text.toString() == ""){
            text_bacName.setError( "กรุณากรอกชื่อบัญชีธนาคาร" )
            result = false
        }
        if(text_batId.text.toString() == ""){
            text_batId.setError("กรุณาเลือกชนิดของบัญชีธนาคาร")
            result = false
        }
        if(text_baId.text.toString() == ""){
            text_baId.setError("กรุณาเลือกบัญชีธนาคาร")
            result = false
        }
        if(text_balance.text.toString() == ""){
            text_balance.setError("กรุณากรอกยอดยกมา")
            result = false
        }

        return result
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
