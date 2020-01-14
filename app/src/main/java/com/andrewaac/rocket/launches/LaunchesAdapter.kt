package com.andrewaac.rocket.launches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrewaac.rocket.R
import com.andrewaac.rocket.db.launch.Launch
import com.andrewaac.rocket.utils.TimeUtils

class LaunchesAdapter :
    RecyclerView.Adapter<LaunchViewHolder>() {

    companion object {
        const val TAG = "LaunchesAdapter"
    }

    private var launchList: ArrayList<Launch> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_rocket_launch, parent, false)
        return LaunchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return launchList.size
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launchList[position])
    }

    override fun onBindViewHolder(
        holder: LaunchViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isNotEmpty()) {
            holder.bind(payloads[0] as Launch)
        }
    }

    fun updateLaunches(launchList: List<Launch>) {
        val launchDiffUtilCallback =
            LaunchDiffUtilCallback(this.launchList, launchList)
        val diffResult = DiffUtil.calculateDiff(launchDiffUtilCallback)
        this.launchList.clear()
        this.launchList.addAll(launchList)
        diffResult.dispatchUpdatesTo(this)
    }
}

class LaunchDiffUtilCallback(var oldList: List<Launch>, var newList: List<Launch>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.flight_number == newItem.flight_number
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        val sameFlightNumber = oldItem.flight_number == newItem.flight_number
        val sameDate = oldItem.launch_date_utc == newItem.launch_date_utc
        return sameFlightNumber && sameDate
    }

}

class LaunchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        const val TAG = "LaunchViewHolder"
    }

    private val missionTitle = view.findViewById<TextView>(R.id.mission_name)
    private val launchSite = view.findViewById<TextView>(R.id.site_name)
    private val missionDate = view.findViewById<TextView>(R.id.mission_date)
    private val missionDetails = view.findViewById<TextView>(R.id.mission_details)

    fun bind(launch: Launch) {
        missionTitle.text = launch.mission_name ?: "N/A"
        launchSite.text = launch.launch_site?.site_name ?: "N/A"
        missionDate.text =
            launch.launch_date_utc?.let { TimeUtils.beautify(it) } ?: kotlin.run { "N/A" }
        missionDetails.text = launch.details ?: "N/A"
    }

}