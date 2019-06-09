package example.bryan.com.docusignhackathon_forestapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_NEUTRAL
import android.support.v7.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context

open class AppActivity : AppCompatActivity() {
    private var dialog: ProgressDialog? = null

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showAlertDialog(title: String = "Error", message: String = "Oh no! Something went wrong!") {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    fun showLoadingDialog() {
        dialog = ProgressDialog(this).apply {
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

    fun saveUserData(name: String, email: String) {
        this.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
                .edit()
                .putString("username", name)
                .putString("useremail", email)
                .apply()
    }

    fun getUserData(): Pair<String, String> {
        val name = this.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
                .getString("username", "")
        val email = this.getSharedPreferences("USERDATA", Context.MODE_PRIVATE)
                .getString("useremail", "")
        return Pair(name, email)
    }
}
