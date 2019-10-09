package com.example.esportslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esportslist.R
import com.example.esportslist.model.eAthlete

class eAthleteAdapter(private val list: List<eAthlete>, private val delgator: eAthleteDelegate):
    RecyclerView.Adapter<eAthleteAdapter.CustomViewHolder>() {
    interface eAthleteDelegate{
        fun getMoreInfo(eathlete: eAthlete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_item_layout, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.apply {
            handleText.text = list[position].handle
            handleText.setOnClickListener{
                delgator.getMoreInfo(list[position])
            }
        }
    }


    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val handleText: TextView = view.findViewById(R.id.tvHandle)
    }
}