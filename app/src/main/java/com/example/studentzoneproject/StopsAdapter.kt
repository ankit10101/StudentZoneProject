package com.example.studentzoneproject

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_row.view.*

class StopsAdapter(private val stops: List<String>) : RecyclerView.Adapter<StopsAdapter.StopHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): StopHolder {
        val inflatedView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row, viewGroup, false)
        return StopHolder(inflatedView)
    }

    override fun getItemCount() = stops.size

    override fun onBindViewHolder(stopHolder: StopHolder, position: Int) {
        val currentStop = stops[position]
        Log.e("TAG", currentStop)
        with(stopHolder.itemView) {
            tvName.text = currentStop
        }
    }

    class StopHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}