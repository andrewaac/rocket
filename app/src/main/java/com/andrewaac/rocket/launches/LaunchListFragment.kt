package com.andrewaac.rocket.launches

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrewaac.rocket.R
import kotlinx.android.synthetic.main.fragment_launch_list.*
import kotlinx.android.synthetic.main.fragment_launch_list.view.*
import kotlinx.coroutines.delay
import java.util.*

class LaunchListFragment : Fragment() {

    lateinit var launchListViewModel: LaunchListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchListViewModel = ViewModelProviders.of(this).get(LaunchListViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        launches_recycler.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down)
        launches_recycler.layoutManager = LinearLayoutManager(context)
        launches_recycler.adapter = LaunchesAdapter()
        launchListViewModel.getLaunchList()?.observe(this, Observer {
            if (it.isNotEmpty()) {
                (launches_recycler.adapter as LaunchesAdapter).updateLaunches(it)
                launches_recycler.scheduleLayoutAnimation()
                progress_bar.visibility = GONE
            }
        })
    }
}
