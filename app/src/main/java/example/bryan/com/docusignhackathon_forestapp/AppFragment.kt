package example.bryan.com.docusignhackathon_forestapp


import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
open class AppFragment : Fragment() {

    private var dialog: ProgressDialog? = null

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showAlertDialog(title: String = "Error", message: String = "Oh no! Something went wrong!") {
        val alertDialog = AlertDialog.Builder(context!!).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    fun showLoadingDialog() {
        dialog = ProgressDialog(context).apply {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setTitle("Loading")
            setMessage("Patience is a virtue!")
            setCanceledOnTouchOutside(true)
            show()
        }
    }

    fun hideLoadingDialog() {
        dialog?.dismiss()
    }

    fun setUserData(name: String, email: String) {
        context?.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
                ?.edit()
                ?.putString("username", name)
                ?.putString("useremail", email)
                ?.apply()
    }

    fun getUserData(): Pair<String, String> {
        val name = context?.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
                ?.getString("username", "") ?: ""
        val email = context?.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
                ?.getString("useremail", "") ?: ""
        return Pair(name, email)
    }
}
