package example.bryan.com.docusignhackathon_forestapp

import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_single_petition.*

class SinglePetitionActivity : AppActivity() {

    private val petitionDesc by lazy {
        intent.getStringExtra(petitionDescriptionKey)
    }

    private val petitionId by lazy {
        intent.getIntExtra(petitionIdKey, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_petition)
        petitionDescription.setText(petitionDesc)
        supportPetitionButton.setOnClickListener {
            onClickSupportPetitionButton()
        }
    }

    fun onClickSupportPetitionButton() {
        val userData = getUserData()
        showLoadingDialog()
        MockService.getPetitionDocusign(
                userData.first,
                userData.second,
                petitionId
        ).doOnEach {
            hideLoadingDialog()
        }.subscribe{
            DocuSignActivity.startActivity(this, it)
        }
    }

    companion object {
        private val petitionIdKey = "id"
        private val petitionDescriptionKey = "description"
        fun startActivity(context: Context, id: Int, description: String) {
            context.startActivity(Intent(context, SinglePetitionActivity::class.java).apply {
                putExtra(petitionIdKey, id)
                putExtra(petitionDescriptionKey, description)
            })
        }
    }

}
