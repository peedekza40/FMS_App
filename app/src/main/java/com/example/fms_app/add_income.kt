package com.example.fms_app

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_add_income.*
import java.util.*
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.AdapterView
import android.widget.Toast
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.AdapterView.OnItemClickListener
import com.example.fms_app.Data_class.BankAccount
import com.example.fms_app.Service.Bank_account
import com.example.fms_app.Service.Income
import com.example.fms_app.Service.TransformDate
import com.example.fms_app.Service.VolleyCallback
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [add_income.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [add_income.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class add_income : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var root: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_add_income, container, false)
        // Inflate the layout for this fragment

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service_income = Income(activity!!.applicationContext,activity!!.cacheDir) //service from phpmyadmin
        val service_bankACC = Bank_account(activity!!.applicationContext, activity!!.cacheDir)
        /*----------------------set inc_code------------------------------------*/
        service_income.get_last_inc_code(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onSuccess(result: String) {
                val last_code = JSONArray(result).getJSONObject(0).getString("inc_code")
                codeText.setText(gen_inc_code(last_code))
            }
        })

        /*---------------------set inc_date-------------------------*/
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        var inc_date = TransformDate(current.format(formatter)).getThaiDate_1()
        dateText.setText(inc_date)

        //date picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        dateText.setOnClickListener {
            val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in Toast
                var dd = dayOfMonth.toString().padStart(2, '0')
                var mm = (monthOfYear + 1).toString().padStart(2, '0')
                dateText.setText(TransformDate("$dd-$mm-$year").getThaiDate_1())
            }, year, month, day)
            dpd.show()
        }

        /*---------------------set bac dropdown-------------------------*/
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
                val bac_test = arrayOf("Ruangwit", "Wongwarit", "Krisana")

                //Create Array Adapter
                val adapter = ArrayAdapter<BankAccount>(activity, android.R.layout.simple_dropdown_item_1line, bac)
                //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                bacText.setAdapter(adapter)
            }
        })
        bacText.setOnFocusChangeListener { v, hasFocus ->
            bacText.showDropDown()
        }
        bacText.setOnClickListener {
            bacText.showDropDown()
        }
        bacText.setOnItemClickListener(OnItemClickListener { arg0, arg1, arg2, arg3->
            bacId.setText(arg0.getItemIdAtPosition(arg2).toInt())
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun gen_inc_code(last_code : String) : String{
        val current = LocalDateTime.now()
        val year_format = DateTimeFormatter.ofPattern("yyyy")
        val month_format = DateTimeFormatter.ofPattern("MM")
        var inc_code = ""

        var year =  (current.format(year_format).toInt() + 543).toString().substring(2)
        var month = current.format(month_format)
        var month_code = last_code.substring(4, 6)
        var year_code = last_code.substring(2, 4)

        if(last_code.equals("") || last_code.equals(null) || (month != month_code) || (year != year_code)){
            inc_code = "RE" + year + month + "1".padStart(4, '0')
        }else{
            var sub_code = (last_code.substring(6).toInt() + 1).toString()
            inc_code = "RE" + year + month + sub_code.padStart(4, '0')
        }

        return inc_code
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment add_income.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            add_income().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
