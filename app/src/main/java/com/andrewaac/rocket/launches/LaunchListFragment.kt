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
import com.andrewaac.rocket.R
import kotlinx.android.synthetic.main.fragment_launch_list.*
import kotlinx.coroutines.delay

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
        launchListViewModel.getLaunchList()?.observe(this, Observer {
            launches_recycler.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down)
            launches_recycler.layoutManager = LinearLayoutManager(context)
            launches_recycler.adapter = LaunchesAdapter(it)
            progress_bar.visibility = GONE
        })
    }

    companion object {
        const val TAG = "LAUNCH_LIST"
    }
}
