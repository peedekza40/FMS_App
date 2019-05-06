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
import android.widget.AdapterView
import android.widget.Toast
import android.widget.ArrayAdapter

import android.widget.AutoCompleteTextView
import android.widget.AdapterView.OnItemClickListener








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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*----------------date picker------------------*/
        dateText.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in Toast
                Toast.makeText(activity, """$dayOfMonth - ${monthOfYear + 1} - $year""", Toast.LENGTH_LONG).show()
                dateText.setText("""$dayOfMonth - ${monthOfYear + 1} - $year""")
            }, year, month, day)
            dpd.show()
        }
        /*----------------dropdown------------------*/
        val bac_test = arrayOf("Ruangwit", "Wongwarit", "Krisana")
        //Create Array Adapter
        val adapter = ArrayAdapter<String>(activity, android.R.layout.simple_dropdown_item_1line, bac_test)
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        bacText.setAdapter(adapter)
        bacText.setOnFocusChangeListener { v, hasFocus ->
            bacText.showDropDown()
        }
        bacText.setOnClickListener {
            bacText.showDropDown()
        }
        bacText.setOnItemClickListener(OnItemClickListener { arg0, arg1, arg2, arg3 ->
            Toast.makeText(activity, arg0.getItemAtPosition(arg2) as CharSequence, Toast.LENGTH_LONG).show()
        })
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
