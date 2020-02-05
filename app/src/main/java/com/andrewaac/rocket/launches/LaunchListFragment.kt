package com.andrewaac.rocket.launches

import android.os.Bundle
import android.util.Log
import android.view.View.*
import android.view.animation.AnticipateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.andrewaac.rocket.R
import com.andrewaac.rocket.db.launch.Launch
import kotlinx.android.synthetic.main.fragment_launch_list.*

class LaunchListFragment : Fragment(R.layout.fragment_launch_list) {

    companion object {
        const val TAG = "LaunchListFragment"
    }

    private lateinit var launchListViewModel: LaunchListViewModel
    private var menuOpened = false
    private var launchListAdapter = LaunchesAdapter()
    private var launchesObserver = Observer<List<Launch>> {
        Log.d(TAG, "New list of size: ${it.size}")
        if (it.isNotEmpty()) {
            launchListAdapter.updateLaunches(it)
            progress_bar.visibility = GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchListViewModel = ViewModelProviders.of(this).get(LaunchListViewModel::class.java)
        launchListViewModel.getLaunchList()?.observe(this, launchesObserver)
        launchListViewModel.getFilteredList().observe(this, launchesObserver)
    }


    override fun onResume() {
        super.onResume()
        launches_recycler.adapter = launchListAdapter
        launchListViewModel.getLaunches()
        fab_menu.setOnClickListener {
            if (!menuOpened) {
                openMenu()
            } else {
                closeMenu()
            }
        }
        by_completed.setOnClickListener {
            launchListViewModel.filterListByCompleted()
            closeMenu()
        }
        by_date.setOnClickListener {
            launchListViewModel.orderListByCompleted()
            closeMenu()
        }
    }

    private fun openMenu() {
//        by_completed.startAnimation(AnimUtils.getTopFABEnterAnimation())
//        by_date.startAnimation(AnimUtils.getBottomFABEnterAnimation())
        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_launch_list_expand)
        val transition: Transition = ChangeBounds().apply {
            interpolator = OvershootInterpolator()
            duration = 750
            addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    by_completed_label.visibility = VISIBLE
                    by_date_label.visibility = VISIBLE
                }

                override fun onTransitionResume(transition: Transition) {}
                override fun onTransitionPause(transition: Transition) {}
                override fun onTransitionCancel(transition: Transition) {}
                override fun onTransitionStart(transition: Transition) {}

            })
        }
        TransitionManager.beginDelayedTransition(constraint_layout, transition)
        constraintSet.applyTo(constraint_layout)
        menuOpened = true
    }

    private fun closeMenu() {
//        by_completed.startAnimation(AnimUtils.getTopFABExitAnimation())
//        by_date.startAnimation(AnimUtils.getBottomExitAnimation())
        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_launch_list)
        val transition: Transition = ChangeBounds().apply {
            interpolator = AnticipateInterpolator()
            duration = 750
            addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    by_completed_label.visibility = INVISIBLE
                    by_date_label.visibility = INVISIBLE
                }

                override fun onTransitionResume(transition: Transition) {}
                override fun onTransitionPause(transition: Transition) {}
                override fun onTransitionCancel(transition: Transition) {}
                override fun onTransitionStart(transition: Transition) {}

            })
        }
        TransitionManager.beginDelayedTransition(constraint_layout, transition)
        constraintSet.applyTo(constraint_layout)
        menuOpened = false
    }

}
