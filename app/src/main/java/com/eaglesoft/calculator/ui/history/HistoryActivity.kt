package com.eaglesoft.calculator.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.eaglesoft.calculator.R
import com.eaglesoft.calculator.ui.history.fragment.HistoryListFragment

class HistoryActivity : AppCompatActivity() {


    companion object {
        private val TAG = "HistoryActivity"
        val TAG_LIST = "list"
        fun getStartIntent(context: Context?, value: ArrayList<String>?): Intent {
            val intent = Intent(context, HistoryActivity::class.java)
            intent.putExtra(TAG_LIST,value )
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        replaceFragment(HistoryListFragment(getString(R.string.app_name),intent.getStringArrayListExtra(
            TAG_LIST
        )))
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment, fragment.javaClass.simpleName)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}