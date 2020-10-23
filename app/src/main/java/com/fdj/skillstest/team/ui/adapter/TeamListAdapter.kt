package com.fdj.skillstest.team.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fdj.skillstest.R
import com.fdj.skillstest.interface_adapter.team.model.TeamUIModel
import com.fdj.skillstest.util.inflateFromParent
import com.fdj.skillstest.util.loadImage
import kotlinx.android.synthetic.main.team_item.view.*

class TeamListAdapter(
    private val onTeamSelected: (TeamUIModel) -> Unit
) : RecyclerView.Adapter<TeamListAdapter.TeamListViewHolder>() {

    var teamList: MutableList<TeamUIModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        return TeamListViewHolder(parent, onTeamSelected)
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        val dailyWeatherView = teamList[position]
        holder.bind(dailyWeatherView)
    }

    override fun getItemCount(): Int = teamList.size

    class TeamListViewHolder(
        itemView: ViewGroup,
        private val onTeamSelected: (TeamUIModel) -> Unit
    ) : RecyclerView.ViewHolder(itemView.inflateFromParent(R.layout.team_item)) {
        fun bind(teamUIModel: TeamUIModel) = with(itemView) {
            team_logo.loadImage(teamUIModel.badgeUrl)
            setOnClickListener { onTeamSelected.invoke(teamUIModel) }
        }
    }
}
