package com.eaglesoft.calculator.ui.history.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.eaglesoft.calculator.R
import kotlinx.android.synthetic.main.fragment_history_list.*

/**
 * A fragment representing a list of Items.
 */
class HistoryListFragment(
    val string: String?,
    val value: ArrayList<String>?
) : Fragment(R.layout.fragment_history_list) {

    private var adapter: MyItemRecyclerViewAdapter? = null
    private val TAG = "HistoryListFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, "onViewCreated: ${string}")

        adapter = MyItemRecyclerViewAdapter(context)
        rv_history.layoutManager = GridLayoutManager(context, 1)
        rv_history.adapter = adapter
        if (value != null) {
            adapter?.setData(value)
            tv_message.visibility = View.GONE
        } else {
            tv_message.visibility = View.VISIBLE
            Log.e(TAG, "onViewCreated: empty list")
        }
    }
}