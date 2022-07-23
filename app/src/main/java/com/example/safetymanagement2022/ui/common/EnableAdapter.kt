package com.example.safetymanagement2022.ui.common

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.KEY_ENABLE

@BindingAdapter("setEnable")
fun setEnable(view: TextView, enable: Int) {
    if (enable == KEY_ENABLE) {
        view.isEnabled = false
        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.app_gray))
    }
}
