package com.ithome11.jetpackmvvmdemo.main.ui.preview

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ithome11.jetpackmvvmdemo.R

@BindingAdapter("app:textCurrentColor")
fun textCurrentColor(view: View, intMessage: Int) {
    val colorBlack = ContextCompat.getColor(view.context, android.R.color.black)
    val colorPrimary = ContextCompat.getColor(view.context, R.color.colorPrimary)
    if (intMessage > 5)
        (view as TextView).setTextColor(colorPrimary)
    else
        (view as TextView).setTextColor(colorBlack)
}