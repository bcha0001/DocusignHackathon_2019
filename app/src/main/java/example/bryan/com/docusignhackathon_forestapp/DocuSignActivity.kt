package example.bryan.com.docusignhackathon_forestapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_docu_sign.*

class DocuSignActivity : AppActivity() {

    private val url by lazy {
        intent.getStringExtra(docusignURLKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_docu_sign)
        docusignWindow.loadUrl(url)
    }

    companion object {
        private val docusignURLKey = "URL_KEY"
        fun startActivity(context: Context, URL: String) {
            context.startActivity(Intent(context, DocuSignActivity::class.java).apply {
                putExtra(docusignURLKey, URL)
            })
        }
    }
}
