package com.andrewaac.rocket.launches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andrewaac.rocket.R
import com.andrewaac.rocket.db.launch.Launch
import com.andrewaac.rocket.utils.TimeUtils

class LaunchesAdapter(private val launchList: List<Launch>) :
    RecyclerView.Adapter<LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_launch, parent, false)
        return LaunchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return launchList.size
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launchList[position])
    }
}

class LaunchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val missionTitle = view.findViewById<TextView>(R.id.mission_title)
    private val missionDate = view.findViewById<TextView>(R.id.mission_date)
    private val missionDetails = view.findViewById<TextView>(R.id.mission_details)

    fun bind(launch: Launch) {
        missionTitle.text = launch.mission_name
        missionDate.text = TimeUtils.beautify(launch.launch_date_utc)
    }

}