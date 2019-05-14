package com.example.fms_app.Manage_base_data

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class BaseDataPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment? {
        when(p0){
            0 -> {
                return Manage_bank()
            }
            1 -> {
                return Manage_bankAccount()
            }
            2 -> {
                return Manage_description()
            }
        }
        return null
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {
                return "Bank"
            }
            1 -> {
                return "Bank Account"
            }
            2 -> {
                return "Description"
            }
        }
        return null
    }
}