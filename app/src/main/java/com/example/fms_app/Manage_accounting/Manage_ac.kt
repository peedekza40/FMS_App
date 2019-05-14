package com.example.fms_app.Manage_accounting

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fms_app.R
import kotlinx.android.synthetic.main.fragment_manage_ac.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Manage_ac.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Manage_ac.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Manage_ac : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private val TAG = "SERVICE_Report"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //code here .....
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_ac, container, false)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager: ViewPager = manage_viewpager
        val tabLayout: TabLayout = manage_view
        viewPager.adapter = ManageDataPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)

//       val object_ma: Income = Income(activity!!,cacheDir = activity!!.cacheDir)
//       // val object_des: Description = Description(activity!!,cacheDir = activity!!.cacheDir)
//        /*----------------------get_all_income------------------------------------*/
//        object_ma.get_all(object : VolleyCallback {
//            override fun onSuccess(result: JSONObject) {
//
//            }
//
//            override fun onSuccess(result: String) {
//                var json = JSONArray(result)
//                var data_report = get_data_table("",0.00)
//                val data_table = data_report.mapingData(json)
//                //test_incomedata.text = data_income[0].Code
//                table_recycle_view.layoutManager = LinearLayoutManager(requireActivity())
//                table_recycle_view.adapter = table_Adapter(data_table)
//                //val inc_code = JSONArray(result).getJSONObject(0).getString("inc_code")
//            }
//        })
//


 //   }




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
         * @return A new instance of fragment Manage_ac.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Manage_ac().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

