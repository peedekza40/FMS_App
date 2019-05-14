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
import com.example.fms_app.Data_class.BankAccount
import com.example.fms_app.Data_class.Bank_data
import com.example.fms_app.R
import com.example.fms_app.Service.*
import kotlinx.android.synthetic.main.activity_add_bank_account.*
import kotlinx.android.synthetic.main.fragment_add_income.*
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
            text_baId.setText(result.baName.toString())//TextView in layout is visible = gone
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
            text_batId.setText(result.batName.toString())//TextView in layout is visible = gone
        })

        //---------------------------------- end drop down --------------------------------------------

        actionBar.setDisplayHomeAsUpEnabled(true)
        var input_bacId = findViewById<EditText>(R.id.text_bacId)
        var input_bacNo = findViewById<EditText>(R.id.text_bacNo)
        var input_zbank = findViewById<EditText>(R.id.text_Zbank)
        var input_bacName = findViewById<EditText>(R.id.text_bacName)
        var input_batId = findViewById<EditText>(R.id.text_batId)
        var input_baId = findViewById<EditText>(R.id.text_baId)
        var input_balance = findViewById<EditText>(R.id.text_balance)
        save_btn.setOnClickListener {
//            if(this.validate()){
//                val jsonBody = JSONObject()
//                jsonBody.put("bacId", input_bacId.text.toString())
//                jsonBody.put("bacNum", input_bacNo.text.toString())
//                jsonBody.put("ZBANK", input_zbank.text.toString())
//                jsonBody.put("bacName", input_bacName.text.toString())
//                jsonBody.put("batId", input_batId.text.toString().toInt())
//                jsonBody.put("baId", input_baId.text.toString().toInt())
//                jsonBody.put("bacBalance", input_baId.text.toString().toInt())
//                service_income.insert(jsonBody)
//            }else{
//                Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()
//            }
        }
    }
//    fun validate(): Boolean{
//        var result: Boolean = true
//        if(bacText.text.toString() == ""){
//            bacText.setError( "กรุณาเลือกบัญชีธนาคาร" )
//            result = false
//        }
//        if(amountText.text.toString() == ""){
//            amountText.setError( "กรุณากรอกจำนวนเงิน" )
//            result = false
//        }
//        if(descText.text.toString() == ""){
//            descText.setError("กรุณาเลือกคำอธิบาย")
//            result = false
//        }
//
//        return result
//    }
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
