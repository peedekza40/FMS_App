package com.example.fms_app.Report

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.fms_app.Report_payment
import android.R.attr.fragment
import android.R
import android.R.attr.fragment





class ReportPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){


    override fun getCount(): Int {
        return 2
    }

    override fun getItem(p0: Int): Fragment? {

        when(p0){
            0 -> {
                return Report_income()
            }

            1 ->{
                return Report_payment()
            }

        }

        return null
    }

    override fun getPageTitle(position: Int): CharSequence? {

        when(position){
            0->{
                return "รายรับ"
            }

            1->{
                return "รายจ่าย"
            }
        }

        return null
    }

}