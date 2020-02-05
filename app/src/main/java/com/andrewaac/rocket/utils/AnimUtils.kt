package com.andrewaac.rocket.utils

import android.view.animation.Animation
import android.view.animation.AnticipateInterpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation

class AnimUtils {

    companion object {

        private const val ANIMATION_DURATION = 750L
        private const val ANIMATION_OFFEST = 150L

        fun getTopFABEnterAnimation(animationListener: Animation.AnimationListener? = null): TranslateAnimation {
            return TranslateAnimation(0f, 0f, 0f, -350f).apply {
                duration = ANIMATION_DURATION
                interpolator = OvershootInterpolator()
                startOffset = ANIMATION_OFFEST
                fillAfter = true
                animationListener?.let { setAnimationListener(it) }
            }
        }

        fun getBottomFABEnterAnimation(animationListener: Animation.AnimationListener? = null): TranslateAnimation {
            return TranslateAnimation(0f, 0f, 0f, -200f).apply {
                duration = ANIMATION_DURATION
                interpolator = OvershootInterpolator()
                fillAfter = true
                animationListener?.let { setAnimationListener(it) }
            }
        }

        fun getTopFABExitAnimation(animationListener: Animation.AnimationListener? = null): TranslateAnimation {
            return TranslateAnimation(0f, 0f, -350f, 0f).apply {
                duration = ANIMATION_DURATION
                interpolator = AnticipateInterpolator()
                fillAfter = true
                animationListener?.let { setAnimationListener(it) }
            }
        }

        fun getBottomExitAnimation(animationListener: Animation.AnimationListener? = null): TranslateAnimation {
            return TranslateAnimation(0f, 0f, -200f, 0f).apply {
                interpolator = AnticipateInterpolator()
                duration = ANIMATION_DURATION
                fillAfter = true
                startOffset = ANIMATION_OFFEST
                animationListener?.let { setAnimationListener(it) }
            }
        }

    }
}