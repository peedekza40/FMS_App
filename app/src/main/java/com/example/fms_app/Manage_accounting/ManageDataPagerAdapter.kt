package com.example.fms_app.Manage_accounting

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.fms_app.Manage_base_data.Manage_bank
import com.example.fms_app.Manage_base_data.Manage_bankAccount
import com.example.fms_app.Manage_base_data.Manage_description

class ManageDataPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment? {
        when(p0){
            0 -> {
                return Manage_income()
            }
            1 -> {
                return Manage_payment()
            }

        }
        return null
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {
                return "รายรับ"
            }
            1 -> {
                return "รายจ่าย"
            }

        }
        return null
    }
}