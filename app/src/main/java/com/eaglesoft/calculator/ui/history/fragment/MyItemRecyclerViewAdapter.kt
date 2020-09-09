package com.eaglesoft.calculator.ui.history.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eaglesoft.calculator.R
import kotlinx.android.synthetic.main.fragment_item.view.*

class MyItemRecyclerViewAdapter(
    private val context: Context?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    private var values: List<String>? = null

    fun setData(values: List<String>) {
        this.values = arrayListOf()
        this.values = values
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values = values?.get(position))
    }

    override fun getItemCount(): Int = values?.size ?: 0

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(values: String?) {
            view.tv_value.text = values
        }
    }
}