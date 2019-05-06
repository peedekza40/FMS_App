package com.example.fms_app

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ReportPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(p0: Int): Fragment {

        when(p0){
            0 -> {
                return Report_income()
            }

            1 ->{
                return Report_payment()
            }

        }

        return Report_income()
    }

    override fun getPageTitle(position: Int): CharSequence {

        when(position){
            0->{
                return "income"
            }

            1->{
                return "payment"
            }
        }

        return "income"
    }

}