package example.bryan.com.docusignhackathon_forestapp

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_camp_sign.*
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CampSignFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CampSignFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CampSignFragment : AppFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        */
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camp_sign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestBooking.setOnClickListener{
            setUpRequestBookingButton()
        }

        ArrayAdapter.createFromResource(
                context,
                R.array.national_parks_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            campsign_spinner.adapter = adapter
        }
    }


    fun setUpRequestBookingButton() {
        if (numGuestsPicker.text.toString().toInt() < 1) {
            showToast("You can't go on a trip without yourself!")
            return
        }

        val startDate = checkDate(startCampDatePicker.text.toString())
        val endDate = checkDate(endCampDatePicker.text.toString())

        if (startDate != null && endDate != null) {
            Log.d("Dates are good" , "Dates are good")
        }
        else {
            return
        }

        val userData = getUserData()
        /*
        //Call the page.
        showLoadingDialog()
        AppService.create().getCampReservationDocusign(
                userData.first,
                userData.second,
                campsign_spinner.selectedItem.toString(),
                numGuestsPicker.text.toString().toInt(),
                startDate,
                endDate
        ).doOnEach {
            hideLoadingDialog()
        }
        .subscribe {
            DocuSignActivity.startActivity(context!!, it)
        }
        */
        showLoadingDialog()
        MockService.getCampReservationDocusign(
                userData.first,
                userData.second,
                campsign_spinner.selectedItem.toString(),
                numGuestsPicker.text.toString().toInt(),
                startDate,
                endDate
        ).doOnEach {
            hideLoadingDialog()
        }
        .subscribe {
            DocuSignActivity.startActivity(context!!, it)
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onCampSignFragmentInteraction(uri)
    }

    fun checkDate(dateText: String): Date? {
        val backSlashCount = dateText.count {
            (it == '/')
        }
        if (backSlashCount != 2) {
            showToast("Badly formatted date!")
            return null
        }
        val date = dateText.split('/')
        val year = date[2]
        if (year.length != 4) {
            showToast("Badly formatted date!")
            return null
        }

        val format = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
        return format.parse(dateText)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

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
        fun onCampSignFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CampSignFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = CampSignFragment()
        /*
        fun newInstance(param1: String, param2: String) =
                CampSignFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        */
    }
}
