package example.bryan.com.docusignhackathon_forestapp.LoginSignup

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import example.bryan.com.docusignhackathon_forestapp.AppActivity
import example.bryan.com.docusignhackathon_forestapp.DocuSignActivity
import example.bryan.com.docusignhackathon_forestapp.MainActivity
import example.bryan.com.docusignhackathon_forestapp.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener{
            MainActivity.startActivity(this)
            saveUserData(nameLogin.text.toString(), emailLogin.text.toString())
        }
        signUpText.setOnClickListener {
            SignupActivity.startActivity(this)
        }
    }


}
