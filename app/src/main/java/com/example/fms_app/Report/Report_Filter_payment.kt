package com.example.fms_app.Report

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.fms_app.R
import kotlinx.android.synthetic.main.activity_report_filter.*

class Report_Filter_payment : AppCompatActivity() {

    var date_start: String? = null
    var date_end: String? = null
    var bacId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_filter)

        button_search.setOnClickListener {
            this.date_start = input_date_start.text.toString()
            this.date_end = input_date_end.text.toString()
            //this.bacId = spinner_account.
        }

        button_cancel.setOnClickListener {
            finish()
        }
    }

}