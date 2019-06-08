package example.bryan.com.docusignhackathon_forestapp

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CampSignFragment.OnFragmentInteractionListener, DonateFragment.OnFragmentInteractionListener, SupportFragment.OnFragmentInteractionListener {
    override fun onCampSignFragmentInteraction(uri: Uri) {
        Log.d("CampSign", uri.toString())
    }

    override fun onDonateFragmentInteraction(uri: Uri) {
        Log.d("Donate", uri.toString())
    }

    override fun onSupportFragmentInteraction(uri: Uri) {
        Log.d("Support", uri.toString())
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_campsign -> {
                openFragment(CampSignFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_support -> {
                openFragment(SupportFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                openFragment(DonateFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contentFrame, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
