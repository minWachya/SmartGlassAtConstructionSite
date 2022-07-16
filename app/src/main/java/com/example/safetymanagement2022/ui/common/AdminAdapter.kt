package com.example.safetymanagement2022.ui.common

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.safetymanagement2022.common.KEY_MANAGER

@BindingAdapter("visibleManager")
fun setVisibleManager(view: View, admin: Int) {
    view.visibility = if(admin == KEY_MANAGER) View.VISIBLE else View.GONE
}

@BindingAdapter("invisibleManager")
fun setInvisibleManager(view: View, admin: Int) {
    view.visibility = if(admin == KEY_MANAGER) View.GONE else View.VISIBLE
}