package example.bryan.com.docusignhackathon_forestapp.LoginSignup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import example.bryan.com.docusignhackathon_forestapp.AppActivity
import example.bryan.com.docusignhackathon_forestapp.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        signUpButton.setOnClickListener{
            signUpListener()
        }
    }

    fun signUpListener() {

        if(!name.text.isNotBlank()) {
            Log.d("Name", name.text.toString())
            showToast("Name can't be empty!")
            return
        }

        if(!email.text.isNotBlank()) {
            showToast("Email can't be empty!")
            return
        }

        if(password.text.toString() != passwordConfirm.text.toString()) {
            Log.d("Password", password.text.toString())
            Log.d("PasswordConfirm", passwordConfirm.text.toString())
            showToast("Passwords don't match!")
            return
        }

        saveUserData(name.text.toString(), email.text.toString())
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SignupActivity::class.java))
        }
    }
}
