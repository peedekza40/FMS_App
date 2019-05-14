package com.example.fms_app.Manage_accounting

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.fms_app.R
import kotlinx.android.synthetic.main.table_list.view.*

class table_Adapter_py (val datasource: ArrayList<get_data_table_py >) : RecyclerView.Adapter<ViewHolder_py>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder_py {
        val mView = LayoutInflater.from(p0.context)
        val mViewHolder = ViewHolder_py(mView, p0)

        return mViewHolder
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(p0: ViewHolder_py, p1: Int) {
        val reportData_py: get_data_table_py = datasource[p1]
        p0.binding(reportData_py)
    }

}

class ViewHolder_py(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.table_list, parent, false)) {
    var detail_table: TextView? = null

    var deatail_amount: TextView? = null

    init {
        detail_table = itemView.deatail_table

        deatail_amount = itemView.deatail_amount

    }

    fun binding(get_data_table_py: get_data_table_py) {
       detail_table?.text = get_data_table_py.desc_description

        deatail_amount?.text = get_data_table_py.pay_amount.toString()
    }
}
