package com.andrewaac.rocket.launches

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrewaac.rocket.BR
import com.andrewaac.rocket.R
import com.andrewaac.rocket.databinding.ViewholderRocketLaunchBinding
import com.andrewaac.rocket.db.launch.Launch

class LaunchesAdapter :
    RecyclerView.Adapter<LaunchViewHolder>() {

    companion object {
        const val TAG = "LaunchesAdapter"
    }

    private var launchList: ArrayList<Launch> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = DataBindingUtil.inflate<ViewholderRocketLaunchBinding>(
            layoutInflater,
            R.layout.viewholder_rocket_launch,
            parent,
            false
        )
        return LaunchViewHolder(itemBinding)
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
        Log.d(TAG, "Payloads size: ${payloads.size}")
        if (payloads.isNotEmpty()) {
            holder.bind(payloads[0] as Launch)
        }
    }

    fun updateLaunches(launchList: List<Launch>) {
        Log.d(
            TAG,
            "Comparing our currentlist (${this.launchList.size}) to new list (${launchList.size})"
        )
        val launchDiffUtilCallback =
            LaunchDiffUtilCallback(this.launchList, launchList)
        val diffResult = DiffUtil.calculateDiff(launchDiffUtilCallback)
        this.launchList.clear()
        this.launchList.addAll(launchList)
        diffResult.dispatchUpdatesTo(this)
    }
}

class LaunchDiffUtilCallback(private var oldList: List<Launch>, private var newList: List<Launch>) :
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

class LaunchViewHolder(private val itemBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(launch: Launch) {
        itemBinding.setVariable(BR.launch, launch)
    }
}