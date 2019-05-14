package com.example.fms_app.Manage_accounting

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_add_income.*
import java.util.*
import android.app.DatePickerDialog
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Editable
import android.widget.Toast
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemClickListener
import com.example.fms_app.Data_class.BankAccount
import com.example.fms_app.Data_class.Desc
import com.example.fms_app.Service.*
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList
import android.text.TextWatcher
import com.example.fms_app.R


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
        //service
        val service_income = Income(activity!!.applicationContext,activity!!.cacheDir)
        val service_bankACC = Bank_account(activity!!.applicationContext, activity!!.cacheDir)
        val service_desc = Description(activity!!.applicationContext, activity!!.cacheDir)
        //Data class



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
        /*---------------------set datepicker-------------------------*/
        this.setDatePicker(dateText)//inc_date
        this.setDatePicker(receiptDateText)//inc_receipt_date

        /*---------------------set bac dropdown-------------------------*/
        bacText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                bacText.setError(null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

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
                val adapter = ArrayAdapter<BankAccount>(activity, android.R.layout.simple_dropdown_item_1line, bac)
                bacText.setAdapter(adapter)
            }
        })
        bacText.setOnFocusChangeListener { v, hasFocus ->
            bacText.showDropDown()
        }
        bacText.setOnClickListener {
            bacText.showDropDown()
        }

        var obj: BankAccount? = null
        bacText.setOnItemClickListener(OnItemClickListener { arg0, arg1, arg2, arg3->
            val result = arg0.getItemAtPosition(arg2) as BankAccount
            bacId.setText(result.bacId.toString())//TextView in layout is visible = gone
        })

        /*---------------------set desc dropdown-------------------------*/
        descText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                descText.setError(null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        service_desc.get_desc_by_desctype(1, object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                val desc_frm_srv = JSONArray(result)
                val desc = ArrayList<Desc>()

                (0 until desc_frm_srv.length()).mapTo(desc) {
                    Desc(
                        desc_frm_srv.getJSONObject(it).getInt("desc_id"),
                        desc_frm_srv.getJSONObject(it).getString("desc_desid"),
                        desc_frm_srv.getJSONObject(it).getString("desc_description"),
                        desc_frm_srv.getJSONObject(it).getInt("desc_type")
                    )
                }
                //Create Array Adapter
                val adapter = ArrayAdapter<Desc>(activity, android.R.layout.simple_dropdown_item_1line, desc)
                descText.setAdapter(adapter)
            }
        })
        descText.setOnFocusChangeListener { v, hasFocus ->
            descText.showDropDown()
        }
        descText.setOnClickListener {
            descText.showDropDown()
        }
        descText.setOnItemClickListener(OnItemClickListener { arg0, arg1, arg2, arg3->
            val result = arg0.getItemAtPosition(arg2) as Desc
            descId.setText(result.desc_id.toString())//TextView in layout is visible = gone
        })



        /*---------------------click save-------------------------*/
        saveBtn.setOnClickListener {
            if(this.validate()){
                val jsonBody = JSONObject()
                jsonBody.put("inc_code", codeText.text.toString())
                jsonBody.put("inc_date", TransformDate(dateText.text.toString()).getDateforDb_1())
                jsonBody.put("inc_receipt", receiptText.text.toString())
                jsonBody.put("inc_receipt_code", receiptCodeText.text.toString())
                jsonBody.put("inc_receipt_date", TransformDate(receiptDateText.text.toString()).getDateforDb_1())
                jsonBody.put("inc_amount", amountText.text.toString().toDouble())
                jsonBody.put("inc_detail", "")
                jsonBody.put("inc_desc_id", descId.text.toString().toInt())
                jsonBody.put("inc_bac_id", bacId.text.toString().toInt())
                service_income.insert(jsonBody)
            }else{
                Toast.makeText(activity, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun validate(): Boolean{
        val service_bankACC = Bank_account(activity!!.applicationContext, activity!!.cacheDir)
        var result: Boolean = true
        if(bacText.text.toString() == ""){
            bacText.setError( "กรุณาเลือกบัญชีธนาคาร" )
            result = false
        }
        if(amountText.text.toString() == ""){
            amountText.setError( "กรุณากรอกจำนวนเงิน" )
            result = false
        }
        if(descText.text.toString() == ""){
            descText.setError("กรุณาเลือกคำอธิบาย")
            result = false
        }

        return result
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
            val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in Toast
                var dd = dayOfMonth.toString().padStart(2, '0')
                var mm = (monthOfYear + 1).toString().padStart(2, '0')
                editText.setText(TransformDate("$dd-$mm-$year").getThaiDate_1())
            }, year, month, day)
            dpd.show()
        }
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
        Income(activity!!, activity!!.cacheDir).cancleRequest()
        Description(activity!!, activity!!.cacheDir).cancleRequest()
        Bank_account(activity!!, activity!!.cacheDir).cancleRequest()
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
