package com.andrewaac.rocket

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        val rotateAnim = AnimationUtils.loadAnimation(context, R.anim.rotate_rocket)
        rotateAnim.setAnimationListener(object : AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                Handler().post {
                    findNavController().navigate(R.id.action_splashFragment_to_launchListFragment)
                }
            }

            override fun onAnimationStart(animation: Animation?) {
                title.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out))
            }
        })
        Handler().postDelayed({ rocket.startAnimation(rotateAnim) }, DELAY_ANIM)
    }

    companion object {
        const val DELAY_ANIM = 1500L
    }
}
