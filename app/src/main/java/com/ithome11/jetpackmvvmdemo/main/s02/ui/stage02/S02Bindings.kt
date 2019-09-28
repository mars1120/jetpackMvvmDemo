package com.ithome11.jetpackmvvmdemo.main.s02.ui.stage02

import android.animation.Animator
import android.util.Log
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

interface LottieOnAnimationEnd {
    fun onAnimationEnd(v: LottieAnimationView)
}

@BindingAdapter("onAnimationEnd")
fun bind_onAnimationEnd(v: LottieAnimationView, listener: LottieOnAnimationEnd) {
    Log.d("bind_onAnimationEnd", "binding")

    v.addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) = Unit

        override fun onAnimationEnd(animation: Animator?) {
            listener.onAnimationEnd(v)
        }

        override fun onAnimationCancel(animation: Animator?) = Unit

        override fun onAnimationStart(animation: Animator?) = Unit

    })

}

@BindingAdapter("animation_speed")
fun bind_animSpeed(v: LottieAnimationView, speed: Float) {
    v.speed = speed
}