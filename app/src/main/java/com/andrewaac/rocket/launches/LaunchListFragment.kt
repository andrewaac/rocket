package com.andrewaac.rocket.launches

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andrewaac.rocket.R

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
            for (launch in it) {
                Log.d(TAG, "$launch")
            }
        })
    }

    companion object {
        const val TAG = "LAUNCH_LIST"
    }
}
