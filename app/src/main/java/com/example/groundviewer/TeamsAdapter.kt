package com.example.groundviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class TeamsAdapter(val teamsList: TeamList): RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_view, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return teamsList.teams.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val team = teamsList.teams.get(position)

        val teamBadgeImageView = holder.view.teamBadgeImg
        Picasso.with(holder.view.context).load(team.strTeamBadge).into(teamBadgeImageView)
        holder.view.teamNameTxt.text = team.strTeam
        holder.view.teamFoundedTxt.text = team.intFormedYear
        holder.view.stadiumNameTxt.text = team.strStadium
    }

}

class CustomViewHolder(val view:View): RecyclerView.ViewHolder(view) {

}
