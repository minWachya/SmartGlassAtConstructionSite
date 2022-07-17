package com.example.safetymanagement2022.ui.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.safetymanagement2022.R

@BindingAdapter("setBuildingText")
fun setBuildingText(view: TextView, buildingName: String?) {
    if (buildingName == null) view.text = "미사용중"
    else view.text = buildingName
}

@BindingAdapter("setUserNameText")
fun setUserNameText(view: TextView, userName: String?) {
    if (userName == null) view.text = "" else view.text = userName
}

@BindingAdapter("setImage")
fun setImage(view: ImageView, userName: String?) {
    if (userName != null) view.setImageResource(R.drawable.ic_smartglass_on)
    else view.setImageResource(R.drawable.ic_smartglass_off)
}

