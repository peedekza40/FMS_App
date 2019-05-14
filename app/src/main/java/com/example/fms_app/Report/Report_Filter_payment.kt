package com.example.fms_app.Report

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.example.fms_app.Data_class.BankAccount
import com.example.fms_app.R
import com.example.fms_app.Report_payment
import com.example.fms_app.Service.Bank_account
import com.example.fms_app.Service.TransformDate
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.activity_report_filter_payment.*
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Report_Filter_payment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_filter_payment)

        /*-----------------set action bar-------------------*/
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "เพิ่มรายการบัญชี"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        // set Service
        val service_bankACC = Bank_account(this, cacheDir)

        //call datepicker
        this.setDatePicker(input_date_start_pay)
        this.setDatePicker(input_date_end_pay)

        // get data and set data to spinner
        service_bankACC.get_bankAccount(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                val bac_frm_svc = JSONArray(result)

                val bac = ArrayList<BankAccount>()

                (0 until bac_frm_svc.length()).mapTo(bac) {
                    BankAccount(
                        bac_frm_svc.getJSONObject(it).getInt("bacId"),
                        bac_frm_svc.getJSONObject(it).getString("bacName")
                    )
                }

                //Create Array Adapter
                val adapter = ArrayAdapter<BankAccount>(this@Report_Filter_payment, android.R.layout.simple_dropdown_item_1line, bac)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                bac_list_pay.setAdapter(adapter)
            }
        })

        // get data from spinner
        bac_list_pay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val result = parent!!.getItemAtPosition(position) as BankAccount
                bac_id_spinner_pay.text = result.bacId.toString()
            }

        }

        button_search_pay.setOnClickListener {
            var date_start = input_date_start_pay.text.toString()
            var date_end = input_date_end_pay.text.toString()
            var bac_id = bac_id_spinner_pay.text.toString()
            var payment_intent = Intent(this@Report_Filter_payment,Report_payment::class.java)
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun setDatePicker(editText: EditText){
        //for set date EditText
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        //for date picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        var date = TransformDate(current.format(formatter)).getThaiDate_1()
        editText.setText(date)

        editText.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in Toast
                var dd = dayOfMonth.toString().padStart(2, '0')
                var mm = (monthOfYear + 1).toString().padStart(2, '0')
                editText.setText(TransformDate("$dd-$mm-$year").getThaiDate_1())
            }, year, month, day)
            dpd.show()
        }
    }

//    fun validate(): Boolean{
//        //val service_bankACC = Bank_account(activity!!.applicationContext, activity!!.cacheDir)
//        var result: Boolean = true
//        if(TransformDate(input_date_end.text.toString()).getDateforDb_1() < TransformDate(input_date_start.text.toString()).getDateforDb_1()){
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

}
