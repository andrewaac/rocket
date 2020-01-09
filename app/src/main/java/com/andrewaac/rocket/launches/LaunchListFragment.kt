package com.andrewaac.rocket.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.andrewaac.rocket.R

class LaunchListFragment : Fragment() {

    lateinit var launchListViewModel: LaunchListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchListViewModel = ViewModelProviders.of(this).get(LaunchListViewModel::class.java)
    }

    companion object {
        const val TAG = "LAUNCH_LIST"
    }
}
