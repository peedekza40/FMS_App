package com.example.fms_app.Report

import android.app.DatePickerDialog
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.widget.ArrayAdapter
import android.widget.EditText
import com.example.fms_app.Data_class.BankAccount
import com.example.fms_app.R
import com.example.fms_app.Service.Bank_account
import com.example.fms_app.Service.TransformDate
import com.example.fms_app.Service.VolleyCallback
import kotlinx.android.synthetic.main.activity_report_filter.*
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Report_Filter_income : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_filter)

        val service_bankACC = Bank_account(this, cacheDir)
        //var report_income: Fragment = Report_income()

        this.setDatePicker(input_date_start)
        this.setDatePicker(input_date_end)

        button_search.setOnClickListener {
            var date_start = input_date_start.text.toString()
            var date_end = input_date_end.text.toString()
            //this.bacId = spinner_account.

            //var arg_income = Bundle()
            //arg_income.putString("inc_startDate",date_start)
            //arg_income.putString("inc_endDate",date_end)
            //arg_income.putString("inc_startDate",date_start)

            //report_income.arguments = arg_income
            finish()
        }

        button_cancel.setOnClickListener {
            finish()
        }

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
                val adapter = ArrayAdapter<BankAccount>(this@Report_Filter_income, android.R.layout.simple_dropdown_item_1line, bac)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                bac_list.setAdapter(adapter)
            }
        })
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

}
