package com.example.esportslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.esportslist.R
import com.example.esportslist.model.eAthlete

class eAthleteAdapter(val list: List<eAthlete>) :
    RecyclerView.Adapter<eAthleteAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): eAthleteAdapter.CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_item_layout, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: eAthleteAdapter.CustomViewHolder, position: Int) {
        holder.apply {

        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.tvName)
        val gameText: TextView = view.findViewById(R.id.tvGame)
        val handleText: TextView = view.findViewById(R.id.tvHandle)
        val teamText: TextView = view.findViewById(R.id.tvTeam)
        val nationText: TextView = view.findViewById(R.id.tvNation)
    }
}