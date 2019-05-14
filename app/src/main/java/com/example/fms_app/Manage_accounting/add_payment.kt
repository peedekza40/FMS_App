package com.example.fms_app.Manage_accounting

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.fms_app.Data_class.BankAccount
import com.example.fms_app.Data_class.Desc
import com.example.fms_app.R
import com.example.fms_app.Service.*
import kotlinx.android.synthetic.main.fragment_add_income.*
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [add_payment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [add_payment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class add_payment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_payment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //service
        val service_payment = Payment(activity!!.applicationContext,activity!!.cacheDir)
        val service_bankACC = Bank_account(activity!!.applicationContext, activity!!.cacheDir)
        val service_desc = Description(activity!!.applicationContext, activity!!.cacheDir)
        //Data class



        /*----------------------set pay_code------------------------------------*/
        service_payment.get_last_pay_code(object : VolleyCallback {
            override fun onSuccess(result: JSONObject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(result: String) {
                val last_code = JSONArray(result).getJSONObject(0).getString("pay_code")
                codeText.setText(gen_pay_code(last_code))
            }
        })
        /*---------------------set datepicker-------------------------*/
        this.setDatePicker(dateText)//inc_date
        this.setDatePicker(receiptDateText)//inc_receipt_date

        /*---------------------set bac dropdown-------------------------*/
        bacText.addTextChangedListener(object : TextWatcher {
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

        bacText.setOnItemClickListener(AdapterView.OnItemClickListener { arg0, arg1, arg2, arg3 ->
            val result = arg0.getItemAtPosition(arg2) as BankAccount
            bacId.setText(result.bacId.toString())//TextView in layout is visible = gone
        })

        /*---------------------set desc dropdown-------------------------*/
        descText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                descText.setError(null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        service_desc.get_desc_by_desctype(2, object : VolleyCallback {
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
        descText.setOnItemClickListener(AdapterView.OnItemClickListener { arg0, arg1, arg2, arg3 ->
            val result = arg0.getItemAtPosition(arg2) as Desc
            descId.setText(result.desc_id.toString())//TextView in layout is visible = gone
        })



        /*---------------------click save-------------------------*/
        saveBtn.setOnClickListener {
            if(this.validate()){
                val jsonBody = JSONObject()
                jsonBody.put("pay_code", codeText.text.toString())
                jsonBody.put("pay_date", TransformDate(dateText.text.toString()).getDateforDb_1())
                jsonBody.put("pay_receipt", receiptText.text.toString())
                jsonBody.put("pay_receipt_code", receiptCodeText.text.toString())
                jsonBody.put("pay_receipt_date", TransformDate(receiptDateText.text.toString()).getDateforDb_1())
                jsonBody.put("pay_amount", amountText.text.toString().toDouble())
                jsonBody.put("pay_detail", "")
                jsonBody.put("pay_desc_id", descId.text.toString().toInt())
                jsonBody.put("pay_bac_id", bacId.text.toString().toInt())
                service_payment.insert(jsonBody, object : VolleyCallback {
                    override fun onSuccess(result: JSONObject) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onSuccess(result: String) {
                        val status = JSONObject(result).getBoolean("status")
                        if(status){
                            Toast.makeText(activity, "เพิ่มรายการบัญชีสำเร็จ", Toast.LENGTH_LONG).show()
                            activity!!.finish()
                        }
                    }

                })
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
    fun gen_pay_code(last_code : String) : String{
        val current = LocalDateTime.now()
        val year_format = DateTimeFormatter.ofPattern("yyyy")
        val month_format = DateTimeFormatter.ofPattern("MM")
        var inc_code = ""

        var year =  (current.format(year_format).toInt() + 543).toString().substring(2)
        var month = current.format(month_format)
        var month_code = last_code.substring(4, 6)
        var year_code = last_code.substring(2, 4)

        if(last_code.equals("") || last_code.equals(null) || (month != month_code) || (year != year_code)){
            inc_code = "PA" + year + month + "1".padStart(4, '0')
        }else{
            var sub_code = (last_code.substring(6).toInt() + 1).toString()
            inc_code = "PA" + year + month + sub_code.padStart(4, '0')
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
         * @return A new instance of fragment add_payment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            add_payment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
