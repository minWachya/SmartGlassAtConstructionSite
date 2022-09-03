package com.example.safetymanagement2022.ui.common

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.KEY_ABLE
import com.example.safetymanagement2022.common.KEY_ENABLE

@BindingAdapter("setEnable")
fun setEnable(view: TextView, enable: Int) {
    if (enable == KEY_ENABLE) {
        view.isEnabled = false
        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.app_gray))
    }
}

@BindingAdapter("setBgColor")
fun setBgColor(view: View, isConnected: Int?) {
    if (isConnected != null) {
        view.setBackgroundResource(
            if(isConnected == KEY_ABLE) R.drawable.background_btn_gray
            else R.drawable.background_btn_orange
        )
    }
}

@BindingAdapter("setBtnText")
fun setBtnText(view: Button, isConnected: Int?) {
    if (isConnected != null) {
        if(isConnected == KEY_ABLE) view.text = "스마트 글래스 연결하기"
        else view.text = "연결 해제"

    }
}
