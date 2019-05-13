package com.example.fms_app

import android.os.Bundle
import android.provider.Settings.Secure.getString
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import java.io.Serializable

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