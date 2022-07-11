package com.example.safetymanagement2022.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.safetymanagement2022.GlideApp

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    }
}